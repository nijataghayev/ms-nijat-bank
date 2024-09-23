package com.example.msnijatbank.service;

import com.example.msnijatbank.dao.entity.AccountEntity;
import com.example.msnijatbank.dao.entity.PaymentEntity;
import com.example.msnijatbank.dao.repository.AccountRepository;
import com.example.msnijatbank.dao.repository.PaymentRepository;
import com.example.msnijatbank.exceptions.NotFoundException;
import com.example.msnijatbank.mapper.PaymentMapper;
import com.example.msnijatbank.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: nijataghayev
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    public void processPayment(Long accountId, Double paidAmount){
        log.info("ActionLog.processPayment.start");
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(
                        "ACCOUNT_NOT_FOUND",
                        String.format("ActionLog.processPayment.id %s not found", accountId)
                ));
        account.setAmount(account.getAmount() - paidAmount);

        PaymentEntity payment = new PaymentEntity();
        payment.setTotalAmount(paidAmount);
        payment.setPaymentDate(LocalDateTime.now());

        paymentRepository.save(payment);
        accountRepository.save(account);
        log.info("ActionLog.processPayment.end");
    }

    public List<PaymentDto> getAllPayments() {
        log.info("ActionLog.getAllPayments.start");
        List<PaymentEntity> paymentEntityList = paymentRepository.findAll();
        List<PaymentDto> paymentDtoList = paymentEntityList.stream()
                .map(paymentMapper::mapToDto)
                .toList();
        log.info("ActionLog.getAllPayments.end");

        return paymentDtoList;
    }

    public PaymentDto getPayment(Long paymentId) {
        log.info("ActionLog.getPayment.start");
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException(
                        "PAYMENT_NOT_FOUND",
                        String.format("ActionLog.getPayment.id %s not found", paymentId)
                ));
        PaymentDto paymentDto = paymentMapper.mapToDto(paymentEntity);
        log.info("ActionLog.getPayment.start");

        return paymentDto;
    }
}
