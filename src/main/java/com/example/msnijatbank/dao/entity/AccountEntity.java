package com.example.msnijatbank.dao.entity;

import com.example.msnijatbank.enums.Currency;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * @author: nijataghayev
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accNumber;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<PaymentEntity> payments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(accNumber, that.accNumber) && currency == that.currency && Objects.equals(amount, that.amount) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accNumber, currency, amount, user);
    }
}
