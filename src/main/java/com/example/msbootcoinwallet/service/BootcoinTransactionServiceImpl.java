package com.example.msbootcoinwallet.service;

import com.example.msbootcoinwallet.model.BootcoinTransaction;
import com.example.msbootcoinwallet.model.BootcoinWallet;
import com.example.msbootcoinwallet.repository.BootcoinTransactionRepository;
import com.example.msbootcoinwallet.repository.BootcoinWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BootcoinTransactionServiceImpl implements BootcoinTransactionService{

    @Autowired
    private BootcoinTransactionRepository repository;

    @Autowired
    private BootcoinWalletRepository bootcoinWalletRepository;


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

    @Override
    public BootcoinTransaction update(BootcoinTransaction bootcoinTransaction) {
        return repository.save(bootcoinTransaction);
    }

    @Override
    public Boolean makeTranaction(String phoneBuyer, String phoneSeller, BigDecimal amount) {
        BootcoinWallet customerBuyer = bootcoinWalletRepository.findByCellphoneNumber(phoneBuyer);
        BootcoinWallet customerSeller = bootcoinWalletRepository.findByCellphoneNumber(phoneSeller);

        if (customerBuyer == null || customerSeller == null) {
            return false;
        }

        if (customerSeller.getBalance().compareTo(amount) < 0) {
            return false;
        }

        customerSeller.setBalance(customerSeller.getBalance().subtract(amount));
        bootcoinWalletRepository.save(customerSeller);
        customerBuyer.setBalance(customerBuyer.getBalance().add(amount));
        bootcoinWalletRepository.save(customerBuyer);
        return true;
    }
}
