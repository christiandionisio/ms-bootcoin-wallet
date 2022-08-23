package com.example.msbootcoinwallet.service;

import com.example.msbootcoinwallet.model.BootcoinWallet;

import java.util.List;

public interface BootcoinWalletService {

    List<BootcoinWallet> findAll();
    BootcoinWallet findById(String id);
    BootcoinWallet save(BootcoinWallet bootcoinWallet);
    BootcoinWallet update(BootcoinWallet bootcoinWallet);
    void delete(String id);
}
