package com.snackbar.payment.application.usecases;
import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;

import java.math.BigDecimal;

public class CreatePaymentUseCase {

    private final PaymentGateway paymentGateway;

    public CreatePaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Payment createPayment(Payment payment){
        String id = null;
        String paymentStatus = "PENDENTE";
        String externalPaymentId = null;
        Payment localPayment = new Payment(
                id,
                payment.orderId(),
                payment.totalDue(),
                paymentStatus,
                payment.paymentMethod(),
                externalPaymentId
        );

        Payment createdPayment = paymentGateway.createPayment(localPayment);

        return createdPayment;
    }

}
