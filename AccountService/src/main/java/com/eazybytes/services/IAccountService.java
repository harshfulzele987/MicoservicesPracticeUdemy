package com.eazybytes.services;

import com.eazybytes.dto.AccountDto;
import com.eazybytes.dto.CustomerDto;

public interface IAccountService {

    void createAccounts(CustomerDto customerDto);

    CustomerDto fetchCustomerByMobileNumber(String mobileNumber);

    AccountDto updateAccountInformation(AccountDto accountDto);

    boolean deleteCustomerDetails(Long customerId);
}
