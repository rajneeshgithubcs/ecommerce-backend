package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public Payment initiate(@RequestParam String orderId, @RequestParam double amount) {
        return paymentService.initiatePayment(orderId, amount);
    }

    @PostMapping("/verify/{orderId}")
    public Payment verify(@PathVariable String orderId) {
        return paymentService.verifyPayment(orderId);
    }
}
