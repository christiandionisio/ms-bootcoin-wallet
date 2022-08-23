package com.example.msbootcoinwallet.service;

import com.example.msbootcoinwallet.model.CoinConfiguration;

import java.util.List;

public interface CoinConfigurationService {
    List<CoinConfiguration> findAll();
    CoinConfiguration findbyId(String id);
    CoinConfiguration save(CoinConfiguration coinConfiguration);
    CoinConfiguration update(CoinConfiguration coinConfiguration);
    CoinConfiguration findByCoinName(String coinName);
    void delete(String id);
}
