package com.example.msnijatbank.controller;

import com.example.msnijatbank.model.AccountDto;
import com.example.msnijatbank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: nijataghayev
 */

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Get all accounts", description = "Retrieve a list of all accounts")
    @GetMapping
    public List<AccountDto> getAllAcounts() {
        return accountService.getAllAccounts();
    }

    @Operation(summary = "Get a account by ID", description = "Retrieve a account by their ID")
    @GetMapping("/accountId")
    public AccountDto getAccount(Long accountId) {
        return accountService.getAccount(accountId);
    }

    @Operation(
            summary = "Transfer money between accounts",
            description = "Transfers a specified amount of money from one account to another. "
                    + "If the accounts are in the same currency and belong to different users, a commission of 0.1% is applied. "
                    + "If the accounts are in different currencies, a commission of 1% is applied. "
                    + "No commission is applied if the accounts belong to the same user regardless of the currency."
    )
    @PostMapping("/transfer")
    public void payment(@RequestParam String paymentCard, @RequestParam String incomeCard, @RequestParam Double amount) {
        accountService.payment(paymentCard, incomeCard, amount);
    }

    @PostMapping("/create")
    public void createAccount(@RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);
    }
}
