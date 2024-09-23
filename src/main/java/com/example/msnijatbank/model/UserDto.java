package com.example.msnijatbank.model;

import com.example.msnijatbank.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: nijataghayev
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    @Schema(description = "This is name of user", required = true, example = "Nijat")
    private String name;
    @Schema(description = "This is surname of user", required = true, example = "Aghayev")
    private String surname;
    @Schema(description = "This is birthdate of user", example = "2001-01-01")
    private LocalDate birthDate;
    @Schema(description = "This is age of user", example = "21")
    private Integer age;
    @Schema(description = "This is FIN CODE of user", required = true, example = "1A111AA")
    private String finCode;
    private List<Account> accounts;

    @Data
    public static class Account {
        private Long id;
        private String accNumber;
        private Currency currency;
        private Double amount;
    }
}
