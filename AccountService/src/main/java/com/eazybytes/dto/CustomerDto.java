package com.eazybytes.dto;

import com.eazybytes.Entity.Accounts;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Singular;

@Data
public class CustomerDto {

    @NotEmpty(message = "Customer Name cannot be empty")
    @Size(min=5 , max=30)
    private String name;

    @NotEmpty(message = "Customer email cannot be empty")
    @Email(message = "Email address is invalid")
    private String email;

    @NotEmpty(message = "Customer mobile number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})" , message = "Invalid phone number length")
    private String mobileNumber;


    private AccountDto accountDto;
}
