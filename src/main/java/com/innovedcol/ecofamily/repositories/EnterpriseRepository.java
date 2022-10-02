package com.innovedcol.ecofamily.repositories;

import com.innovedcol.ecofamily.entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Optional<Enterprise> findByName(String name);

    Optional<Enterprise> findByDocument(String document);
}