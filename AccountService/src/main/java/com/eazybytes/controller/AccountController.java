package com.eazybytes.controller;

import com.eazybytes.constant.AccountConstants;
import com.eazybytes.dto.AccountDto;
import com.eazybytes.dto.CustomerDto;
import com.eazybytes.dto.ResponseDto;
import com.eazybytes.services.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@Validated
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerdto)
    {
        iAccountService.createAccounts(customerdto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomer(@RequestParam String mobileNumber)
    {
        CustomerDto availableCustomer  = iAccountService.fetchCustomerByMobileNumber(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(availableCustomer);
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<AccountDto> updateAccount(@Valid @RequestBody AccountDto accountDto)
    {
        AccountDto res = iAccountService.updateAccountInformation(accountDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam Long customerId)
    {
        boolean result = iAccountService.deleteCustomerDetails(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200 , AccountConstants.MESSAGE_200));
    }

}
