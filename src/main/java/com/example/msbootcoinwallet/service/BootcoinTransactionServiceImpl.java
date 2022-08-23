package com.example.msbootcoinwallet.service;

import com.example.msbootcoinwallet.model.BootcoinTransaction;
import com.example.msbootcoinwallet.repository.BootcoinTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BootcoinTransactionServiceImpl implements BootcoinTransactionService{

    @Autowired
    private BootcoinTransactionRepository repository;


    @Override
    public List<BootcoinTransaction> findAll() {
        List<BootcoinTransaction> bootcoinTransactionList = new ArrayList<>();
        repository.findAll().forEach(bootcoinTransactionList::add);
        return bootcoinTransactionList;
    }

    @Override
    public BootcoinTransaction findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BootcoinTransaction save(BootcoinTransaction bootcoinTransaction) {
        return repository.save(bootcoinTransaction);
    }
}
