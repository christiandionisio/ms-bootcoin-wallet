package com.example.msbootcoinwallet.service;

import com.example.msbootcoinwallet.model.BootcoinTransaction;

import java.util.List;

public interface BootcoinTransactionService {
    List<BootcoinTransaction> findAll();
    BootcoinTransaction findById(String id);
    BootcoinTransaction save(BootcoinTransaction bootcoinTransaction);
    BootcoinTransaction update(BootcoinTransaction bootcoinTransaction);
    Boolean makeTranaction(BootcoinTransaction bootcoinTransaction);
}
