package com.example.msbootcoinwallet.repository;

import com.example.msbootcoinwallet.model.BootcoinWallet;
import org.springframework.data.repository.CrudRepository;

public interface BootcoinWalletRepository extends CrudRepository<BootcoinWallet, String> {
    BootcoinWallet findByCellphoneNumber(String number);
}
