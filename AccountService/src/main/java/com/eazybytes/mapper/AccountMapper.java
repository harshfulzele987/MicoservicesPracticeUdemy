package com.eazybytes.mapper;

import com.eazybytes.Entity.Accounts;
import com.eazybytes.dto.AccountDto;

public class AccountMapper {

    public static AccountDto mapToAccountDto(AccountDto accountDto,Accounts accounts)
    {
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        accountDto.setAccountType(accounts.getAccountType());
        return accountDto;
    }

    public static Accounts mapToAccount(AccountDto accountDto,Accounts accounts)
    {
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        accounts.setAccountType(accountDto.getAccountType());
        return accounts;
    }

}
