package com.grishare.repository;

import com.grishare.domain.Bank;
import com.grishare.domain.ExchangeRate;
import com.grishare.dto.ExchangeRateReturnDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>{
    List<ExchangeRate> findByNationIdAndBank(Long nationId, Bank bank);
}
