package com.snackbar.payment.infrastructure.controllers;

import java.math.BigDecimal;

public record CreatePaymentRequest (String orderId, String paymentMethod, BigDecimal totalDue) {
    
}
