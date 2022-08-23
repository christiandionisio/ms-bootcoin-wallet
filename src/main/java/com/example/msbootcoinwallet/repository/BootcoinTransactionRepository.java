package com.example.msbootcoinwallet.repository;

import com.example.msbootcoinwallet.model.BootcoinTransaction;
import org.springframework.data.repository.CrudRepository;

public interface BootcoinTransactionRepository extends CrudRepository<BootcoinTransaction, String> {
}
