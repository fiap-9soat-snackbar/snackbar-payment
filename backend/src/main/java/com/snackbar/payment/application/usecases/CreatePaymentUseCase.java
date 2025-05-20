package com.snackbar.payment.application.usecases;

import com.snackbar.payment.domain.entity.Order;
import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.application.gateways.OrderClient; // Import Feign client

import java.math.BigDecimal;

public class CreatePaymentUseCase {

    // Logging definition
    //private static final Logger logger = LoggerFactory.getLogger(CreatePaymentUseCaseImpl.class);
    
    private final PaymentGateway paymentGateway;

    public CreatePaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Payment createPayment(Payment payment){
    //public Payment createPayment(String orderId, String paymentMethod){
        //logger.info("Obtaining total price for order: {}", order.getTotalPrice);
        BigDecimal totalDue = new BigDecimal("1000"); //order.getTotalPrice();
        //logger.info("Setting payment status and temporary external payment ID:");
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
        //Payment localPayment = new Payment(orderId, totalDue, paymentStatus, paymentMethod, externalPaymentId);
        Payment createdPayment = paymentGateway.createPayment(localPayment);

        return createdPayment;
    }

}
