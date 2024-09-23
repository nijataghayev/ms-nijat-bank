package com.example.msnijatbank.dao.repository;

import com.example.msnijatbank.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: nijataghayev
 */

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
