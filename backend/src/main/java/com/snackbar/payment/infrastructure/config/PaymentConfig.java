package com.snackbar.payment.infrastructure.config;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.application.usecases.*;
import com.snackbar.orderRefactory.application.usecases.OrderUseCase;
import com.snackbar.payment.infrastructure.MpService;
import com.snackbar.payment.infrastructure.controllers.PaymentDTOMapper;
import com.snackbar.payment.infrastructure.controllers.PaymentMPDTOMapper;
import com.snackbar.payment.infrastructure.gateways.PaymentEntityMapper;
import com.snackbar.payment.infrastructure.gateways.PaymentMPEntityMapper;
import com.snackbar.payment.infrastructure.gateways.PaymentRepositoryGateway;
import com.snackbar.payment.infrastructure.persistence.PaymentMPRepository;
import com.snackbar.payment.infrastructure.persistence.PaymentRepository;
import com.snackbar.order.service.OrderService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/* Logging imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;*/

@Configuration
public class PaymentConfig {
    
    // Logging definition
    //private static final Logger logger = LoggerFactory.getLogger(PaymentConfig.class);
    
    /*
    @PostConstruct
    public void init() {
        logger.info("PaymentConfig initialized");
    }*/

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentGateway paymentGateway, OrderService orderService) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new CreatePaymentUseCase(paymentGateway, orderService);
    }

    @Bean
    public ListPaymentsUseCase listPaymentsUseCase(PaymentGateway paymentGateway) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new ListPaymentsUseCase(paymentGateway);
    }

    @Bean
    public WebHookExecution webHookExecution(PaymentGateway paymentGateway) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new WebHookExecution(paymentGateway);
    }
    
    @Bean
    public CreatePaymentMPUseCase createPaymentMPUseCase(PaymentGateway paymentGateway, WebHookExecution webHookExecution) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new CreatePaymentMPUseCase(paymentGateway, webHookExecution);
    }

    @Bean
    public GetPaymentByIdUseCase getPaymentByIdUseCase(PaymentGateway paymentGateway) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new GetPaymentByIdUseCase(paymentGateway);
    }

    @Bean
    public UpdatePaymentExternalIdByIdUseCase updatePaymentExternalIdByIdUseCase(PaymentGateway paymentGateway, GetPaymentByIdUseCase getPaymentByIdUseCase) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new UpdatePaymentExternalIdByIdUseCase(paymentGateway, getPaymentByIdUseCase);
    }

    @Bean
    public GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase(PaymentGateway paymentGateway) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new GetPaymentByExternalIdUseCase(paymentGateway);
    }

    @Bean
    public UpdatePaymentStatusWebhook updatePaymentStatusByExternalIdUseCase(PaymentGateway paymentGateway, GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase, OrderUseCase orderUseCase) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new UpdatePaymentStatusWebhook(paymentGateway, getPaymentByExternalIdUseCase, orderUseCase);
    }


    @Bean
    public MpService createMpService(WebClient webClient) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new MpService(webClient);
    }

    @Bean
    public PaymentGateway paymentGateway(
            PaymentRepository paymentRepository,
            PaymentMPRepository paymentMPRepository,
            PaymentEntityMapper paymentEntityMapper,
            PaymentMPEntityMapper paymentMPEntityMapper,
            MpService mpService
    ) {
        return new PaymentRepositoryGateway(
                paymentRepository,
                paymentMPRepository,
                paymentEntityMapper,
                paymentMPEntityMapper,
                mpService);
    }

    @Bean
    public PaymentEntityMapper paymentEntityMapper() {
        return new PaymentEntityMapper();
    }

    @Bean
    public PaymentMPEntityMapper paymentMPEntityMapper() {
        // Logging
        //logger.info("Creating PaymentEntityMapper bean");
        return new PaymentMPEntityMapper();
    }

    @Bean
    public PaymentDTOMapper paymentDTOMapper() {
        // Logging
        //logger.info("Creating PaymentDTOMapper bean");
        return new PaymentDTOMapper();
    }

    @Bean
    public PaymentMPDTOMapper paymentMPDTOMapper() {
        // Logging
        //logger.info("Creating PaymentDTOMapper bean");
        return new PaymentMPDTOMapper();
    }

}
