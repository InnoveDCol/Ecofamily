package com.innovedcol.ecofamily.repositories;

import com.innovedcol.ecofamily.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
