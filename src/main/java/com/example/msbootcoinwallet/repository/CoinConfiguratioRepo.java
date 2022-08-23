package com.example.msbootcoinwallet.repository;

import com.example.msbootcoinwallet.model.CoinConfiguration;
import org.springframework.data.repository.CrudRepository;

public interface CoinConfiguratioRepo extends CrudRepository<CoinConfiguration, String> {
    CoinConfiguration findByCoinName(String coinName);
}
