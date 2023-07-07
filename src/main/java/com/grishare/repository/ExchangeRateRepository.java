package com.grishare.repository;

import com.grishare.domain.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>{
    ExchangeRate findByExs(Long nId, Long bId);
}
