package com.example.msnijatbank.controller;

import com.example.msnijatbank.model.PaymentDto;
import com.example.msnijatbank.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: nijataghayev
 */

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/process")
    @Operation(summary = "Process a payment")
    public void processPayment(@RequestParam Long accountId, @RequestParam Double paidAmmount){
        paymentService.processPayment(accountId, paidAmmount);
    }

    @GetMapping
    @Operation(summary = "Get all payments", description = "Returns a list of all payments.")
    public List<PaymentDto> getAllPayment() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Get payment by ID", description = "Returns the payment details for the given payment ID.")
    public PaymentDto getPayment(@PathVariable Long paymentId) {
        return paymentService.getPayment(paymentId);
    }
}
