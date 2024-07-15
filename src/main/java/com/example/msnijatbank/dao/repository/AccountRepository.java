package com.example.msnijatbank.dao.repository;

import com.example.msnijatbank.dao.entity.AccountEntity;
import com.example.msnijatbank.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author: nijataghayev
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByAccNumber(String accNumber);

    Optional<AccountEntity> findByUserIdAndCurrency(Long userId, Currency currency);
}
