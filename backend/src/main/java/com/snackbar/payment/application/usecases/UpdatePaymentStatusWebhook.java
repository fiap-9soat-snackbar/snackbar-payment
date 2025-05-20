package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.OrderClient;
import com.snackbar.payment.domain.entity.Order;
//import com.snackbar.orderRefactory.application.usecases.OrderUseCase;
import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;

public class UpdatePaymentStatusWebhook {

    private final OrderClient orderClient;
    private final PaymentGateway paymentGateway;
    private final GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase;
//    private final OrderUseCase orderUseCase;

    public UpdatePaymentStatusWebhook(OrderClient orderClient, PaymentGateway paymentGateway,
                                      GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase) {
        this.orderClient = orderClient;
        this.paymentGateway = paymentGateway;
        this.getPaymentByExternalIdUseCase = getPaymentByExternalIdUseCase;
//        this.orderUseCase = orderUseCase;
    }

    public Payment updatePaymentStatus(String externalId, String paymentStatus) {
        Payment locatedPayment = getPaymentByExternalIdUseCase.getPaymentByExternalId(externalId);
        Payment updatedPayment = paymentGateway.updatePaymentStatusByExternalId(locatedPayment.externalPaymentId(), paymentStatus);
//        orderUseCase.updateStatusOrder(updatedPayment.orderId(), paymentStatus);
        this.orderClient.updateStatusOrder(updatedPayment.orderId(), paymentStatus);
        return updatedPayment;
    }

}
