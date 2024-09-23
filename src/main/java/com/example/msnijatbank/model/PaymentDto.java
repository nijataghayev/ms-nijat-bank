package com.example.msnijatbank.model;

import com.example.msnijatbank.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: nijataghayev
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {
    private Long id;
    private Double totalAmount;
    private LocalDateTime paymentDate;
    private Account account;

    @Data
    public static class Account{
        private Long id;
        private String acc_number;
        private Currency currency;
        private Double amount;
    }
}
