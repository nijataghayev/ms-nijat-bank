package com.example.msnijatbank.dao.repository;

import com.example.msnijatbank.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: nijataghayev
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
