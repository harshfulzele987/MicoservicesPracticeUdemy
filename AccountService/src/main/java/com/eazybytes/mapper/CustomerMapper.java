package com.eazybytes.mapper;

import com.eazybytes.Entity.Accounts;
import com.eazybytes.Entity.Customer;
import com.eazybytes.dto.AccountDto;
import com.eazybytes.dto.CustomerDto;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerDto customerDto, Customer customer)
    {
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto,Customer customer)
    {
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
