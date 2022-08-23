package com.example.msbootcoinwallet.service;

import com.example.msbootcoinwallet.dto.PaymentDto;
import com.example.msbootcoinwallet.model.BootcoinTransaction;
import com.example.msbootcoinwallet.model.BootcoinWallet;
import com.example.msbootcoinwallet.model.CoinConfiguration;
import com.example.msbootcoinwallet.producer.PaymentTransactionProducer;
import com.example.msbootcoinwallet.producer.PaymentWalletProducer;
import com.example.msbootcoinwallet.repository.BootcoinTransactionRepository;
import com.example.msbootcoinwallet.repository.BootcoinWalletRepository;
import com.example.msbootcoinwallet.repository.CoinConfiguratioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BootcoinTransactionServiceImpl implements BootcoinTransactionService{

    @Autowired
    private BootcoinTransactionRepository repository;

    @Autowired
    private BootcoinWalletRepository bootcoinWalletRepository;

    @Autowired
    private PaymentTransactionProducer paymentTransactionProducer;

    @Autowired
    private PaymentWalletProducer paymentWalletProducer;

    @Autowired
    private CoinConfiguratioRepo coinConfiguratioRepo;


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
    public Boolean makeTranaction(BootcoinTransaction bootcoinTransaction) {
        BootcoinWallet customerBuyer = bootcoinWalletRepository.findByCellphoneNumber(bootcoinTransaction.getPhoneBuyer());
        BootcoinWallet customerSeller = bootcoinWalletRepository.findByCellphoneNumber(bootcoinTransaction.getPhoneSeller());

        if (customerBuyer == null || customerSeller == null) {
            return false;
        }

        if (customerSeller.getBalance().compareTo(bootcoinTransaction.getAmountCoin()) < 0) {
            return false;
        }

        customerSeller.setBalance(customerSeller.getBalance().subtract(bootcoinTransaction.getAmountCoin()));
        bootcoinWalletRepository.save(customerSeller);
        customerBuyer.setBalance(customerBuyer.getBalance().add(bootcoinTransaction.getAmountCoin()));
        bootcoinWalletRepository.save(customerBuyer);

        CoinConfiguration bootCoin = coinConfiguratioRepo.findByCoinName("BootCoin");

        if (bootCoin == null) {
            return false;
        }

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(bootcoinTransaction.getAmountCoin().multiply(bootCoin.getPurchasePrice()));
        paymentDto.setPhoneNumberOrigin(bootcoinTransaction.getPhoneBuyer());
        paymentDto.setPhoneNumberDestination(bootcoinTransaction.getPhoneSeller());

        if (bootcoinTransaction.getPaymentMode().equalsIgnoreCase("yanki")) {
            paymentWalletProducer.sendMessage(paymentDto);
        }

        if (bootcoinTransaction.getPaymentMode().equalsIgnoreCase("transferencia")) {
            paymentTransactionProducer.sendMessage(paymentDto);
        }
        return true;
    }
}
