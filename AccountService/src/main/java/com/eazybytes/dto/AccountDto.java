package com.eazybytes.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "Account Number cannot be empty")
    private long accountNumber;

    @NotEmpty(message = "Account Type cannot be empty")
    private String accountType;

    @NotEmpty(message = "Account branch address cannot be empty")
    private String branchAddress;

}
