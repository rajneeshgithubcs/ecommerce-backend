package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;

    public PaymentService(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    public Payment initiatePayment(String orderId, double amount) {

        String transactionId = UUID.randomUUID().toString();

        Payment payment = new Payment(
                orderId,
                amount,
                "PENDING",
                transactionId
        );

        return paymentRepo.save(payment);
    }

    public Payment verifyPayment(String orderId) {

        Payment payment = paymentRepo.findByOrderId(orderId);

        if (payment == null) return null;

        // Dummy behaviour → always success
        payment.setPaymentStatus("SUCCESS");

        return paymentRepo.save(payment);
    }
}
