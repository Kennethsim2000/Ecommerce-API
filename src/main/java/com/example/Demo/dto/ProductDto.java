package com.example.Demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    @NotNull
    private String name;

    private String description;

    @NotNull
    private Integer price;
    private String storeName;
}
