package com.eazybytes.services.Impl;

import com.eazybytes.Entity.Accounts;
import com.eazybytes.Entity.Customer;
import com.eazybytes.constant.AccountConstants;
import com.eazybytes.dto.AccountDto;
import com.eazybytes.dto.CustomerDto;
import com.eazybytes.exceptions.CustomerAlreadyExistException;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.mapper.AccountMapper;
import com.eazybytes.mapper.CustomerMapper;
import com.eazybytes.respository.AccountRespository;
import com.eazybytes.respository.CustomerRepository;
import com.eazybytes.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRespository accountRespository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void createAccounts(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> isAlredyPresent = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(isAlredyPresent.isPresent())
        {
            throw new CustomerAlreadyExistException("Customer Already Exist");
        }
        customer.setCreatedAt(LocalDate.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);

        Accounts newAccountForSavedCustomer = createNewAccountForSavedCustomer(savedCustomer);

        accountRespository.save(newAccountForSavedCustomer);

    }

    @Override
    public CustomerDto fetchCustomerByMobileNumber(String mobileNumber) {
        Optional<Customer> result = customerRepository.findByMobileNumber(mobileNumber);
        if(result.isEmpty())
        {
            throw new ResourceNotFoundException("Customer" , "mobileNumber" , mobileNumber);
        }
        Customer customer = result.get();

        Optional<Accounts> byCustomerId = accountRespository.findByCustomerId(customer.getCustomerId());
        if(byCustomerId.isEmpty())
        {
            throw new ResourceNotFoundException("Account" , "customerId" , String.valueOf(customer.getCustomerId()));
        }
        Accounts accounts = byCustomerId.get();
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto(), customer);
        AccountDto accountDto = AccountMapper.mapToAccountDto(new AccountDto(), accounts);
        customerDto.setAccountDto(accountDto);

        return customerDto;

    }

    @Override
    public AccountDto updateAccountInformation(AccountDto accountDto)
    {
        Optional<Accounts> result = accountRespository.findById(accountDto.getAccountNumber());
        if(result.isEmpty())
        {
            throw new ResourceNotFoundException("Account" , "Accounts" , "account id");
        }
        Accounts updatedAccount = accountRespository.save(AccountMapper.mapToAccount(accountDto, result.get()));
        return AccountMapper.mapToAccountDto(accountDto , updatedAccount);
    }

    @Override
    public boolean deleteCustomerDetails(Long customerId) {
        Optional<Customer> result = customerRepository.findById(customerId);
        if(result.isEmpty())
        {
            throw  new ResourceNotFoundException("Customer" , "Customer id" , "customer_id");
        }else
        {
            CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto() , result.get());
            AccountDto accountDto = customerDto.getAccountDto();
            customerRepository.delete(result.get());
            accountRespository.delete(AccountMapper.mapToAccount(accountDto , new Accounts()));
        }
        return true;
    }

    private Accounts createNewAccountForSavedCustomer(Customer savedCustomer)
    {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(savedCustomer.getCustomerId());
        accounts.setAccountNumber(100000l + new Random().nextInt(90000));

        accounts.setBranchAddress(AccountConstants.ADDRESS);
        accounts.setCreatedBy("Anonymous");
        accounts.setCreatedAt(LocalDate.now());
        accounts.setAccountType(AccountConstants.SAVINGS);

        return accounts;
    }
}
