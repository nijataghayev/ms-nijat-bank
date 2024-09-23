package com.example.msnijatbank.service;

import com.example.msnijatbank.dao.entity.AccountEntity;
import com.example.msnijatbank.dao.repository.AccountRepository;
import com.example.msnijatbank.enums.Currency;
import com.example.msnijatbank.exceptions.NotFoundException;
import com.example.msnijatbank.mapper.AccountMapper;
import com.example.msnijatbank.model.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: nijataghayev
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public void createAccount(AccountDto accountDto) {
        log.debug("ActionLog.createAccount.start account {}", accountDto);

        Long userId = accountDto.getUser().getId();
        Currency currency = accountDto.getCurrency();

        Optional<AccountEntity> existingAccount = accountRepository.findByUserIdAndCurrency(userId, currency);
        if (existingAccount.isPresent()) {
            throw new RuntimeException("User already has an account with this currency.");
        }

        AccountEntity accountEntity = accountMapper.mapToEntity(accountDto);
        String accountNumber = currency.name() + userId;
        accountEntity.setAccNumber(accountNumber);
        accountRepository.save(accountEntity);
        log.debug("ActionLog.createAccount.end account {}", accountDto);
    }

    public List<AccountDto> getAllAccounts() {
        log.info("ActionLog.getAllAccounts.start");
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountDto> accountDto = accountEntities.stream()
                .map(accountMapper::mapToDto)
                .toList();
        log.info("ActionLog.getAllAccounts.end");
        return accountDto;
    }

    public AccountDto getAccount(Long accountId) {
        log.info("ActionLog.getAccount.start accountId {}", accountId);
        var accountEntity = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new NotFoundException(
                        "ACCOUNT_NOT_FOUND",
                        String.format("ActionLog.getAccount.id %d not found", accountId)));
        var accountDto = accountMapper.mapToDto(accountEntity);
        log.info("ActionLog.getAccount.end accountId {}", accountId);
        return accountDto;
    }

    public void cardToCard(String fromCardNumber, String toCardNumber, Double amount) {
        AccountEntity fromCard = accountRepository.findByAccNumber(fromCardNumber);
        AccountEntity toCard = accountRepository.findByAccNumber(toCardNumber);

        if (fromCard.getUser().getId() == toCard.getUser().getId()) {
            Double newAmountFromCard = fromCard.getAmount() - amount;
            fromCard.setAmount(newAmountFromCard);
            accountRepository.save(fromCard);

            Double newAmountToCard = toCard.getAmount() + amount;
            toCard.setAmount(newAmountToCard);
            accountRepository.save(toCard);
        } else {
            if (fromCard.getCurrency() == toCard.getCurrency()) {
                Double commission = amount * 0.1 / 100;
                Double newAmountFromCard = fromCard.getAmount() - amount - commission;
                fromCard.setAmount(newAmountFromCard);
                accountRepository.save(fromCard);

                Double newAmountToCard = toCard.getAmount() + amount;
                toCard.setAmount(newAmountToCard);
                accountRepository.save(toCard);
            } else {
                Double commission = amount * 1 / 100;
                Double newAmount = fromCard.getAmount() - amount - commission;
                fromCard.setAmount(newAmount);
                accountRepository.save(fromCard);

                Double newAmountToCard = toCard.getAmount() + amount;
                toCard.setAmount(newAmountToCard);
                accountRepository.save(toCard);
            }
        }
    }

    public void incomeToAccount(Long accountId, Double incomingAmount) {
        log.info("ActionLog.incomeToAccount.start accountId {}", accountId);
        var accountEntity = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new NotFoundException(
                        "ACCOUNT_NOT_FOUND",
                        String.format("ActionLog.getAccount.id %d not found", accountId)));

        accountEntity.setAmount(accountEntity.getAmount() + incomingAmount);
        accountRepository.save(accountEntity);
        log.info("ActionLog.incomeToAccount.end accountId {}", accountId);
    }
}
