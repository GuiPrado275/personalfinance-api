package com.guilherme.personalfinance.repositories;

import com.guilherme.personalfinance.models.Finance;
import com.guilherme.personalfinance.models.projection.FinanceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {

    List<FinanceProjection> findByUser_Id(Long id); //search Finance by id
}
