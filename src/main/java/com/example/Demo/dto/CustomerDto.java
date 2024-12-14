package com.example.Demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    private Long customerId;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 6,message = "password must be at least 6 letters")
    private String password;

    @NotNull
    private String address;
}
