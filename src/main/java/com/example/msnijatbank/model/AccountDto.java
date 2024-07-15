package com.example.msnijatbank.model;

import com.example.msnijatbank.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author: nijataghayev
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto {
    private Long id;
    @Schema(description = "The account number associated with the user's bank account", required = true)
    private String accNumber;
    @Schema(description = "The currency used in the transaction", required = true)
    private Currency currency;
    @Schema(description = "The monetary amount for the transaction")
    private Double amount;
    private User user;

    @Data
    public static class User{
        private Long id;
        private String name;
        private String surname;
        private LocalDate birthDate;
        private Integer age;
        private String finCode;
    }
}
