package com.example.msbootcoinwallet.service;

import com.example.msbootcoinwallet.model.BootcoinWallet;
import com.example.msbootcoinwallet.repository.BootcoinWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BootcoinWalletServiceImpl implements BootcoinWalletService {

    @Autowired
    private BootcoinWalletRepository repository;

    @Override
    public List<BootcoinWallet> findAll() {
        List<BootcoinWallet> bootcoinWalletList = new ArrayList<>();
        repository.findAll().forEach(bootcoinWalletList::add);
        return bootcoinWalletList;
    }

    @Override
    public BootcoinWallet findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BootcoinWallet save(BootcoinWallet bootcoinWallet) {
        return repository.save(bootcoinWallet);
    }

    @Override
    public BootcoinWallet update(BootcoinWallet bootcoinWallet) {
        return repository.save(bootcoinWallet);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
