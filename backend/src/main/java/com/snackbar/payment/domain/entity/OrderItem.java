package com.snackbar.payment.domain.entity;

public record OrderItem(
    String name,
    Double price,
    Integer quantity,
    Integer cookingTime,
    String customization
) {}
