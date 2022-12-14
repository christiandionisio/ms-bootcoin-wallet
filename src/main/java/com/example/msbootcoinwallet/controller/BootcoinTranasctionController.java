package com.example.msbootcoinwallet.controller;

import com.example.msbootcoinwallet.dto.AcceptExchanceDto;
import com.example.msbootcoinwallet.dto.PaymentDto;
import com.example.msbootcoinwallet.model.BootcoinTransaction;
import com.example.msbootcoinwallet.producer.PaymentTransactionProducer;
import com.example.msbootcoinwallet.producer.PaymentWalletProducer;
import com.example.msbootcoinwallet.service.BootcoinTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bootcoin-tranascation")
public class BootcoinTranasctionController {

    @Autowired
    private BootcoinTransactionService service;



    @GetMapping
    public ResponseEntity<List<BootcoinTransaction>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BootcoinTransaction> findById(@PathVariable String id) {
        BootcoinTransaction bootcoinTransactionBD = service.findById(id);
        return (bootcoinTransactionBD == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(bootcoinTransactionBD);
    }

    @PostMapping
    public ResponseEntity<BootcoinTransaction> save(@RequestBody BootcoinTransaction bootcoinTransaction) {
        bootcoinTransaction.setCreatedAt(LocalDateTime.now());
        bootcoinTransaction.setUpdatedAt(LocalDateTime.now());
        BootcoinTransaction bootcoinTransactionBD = service.save(bootcoinTransaction);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bootcoinTransaction.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<BootcoinTransaction> update(@RequestBody AcceptExchanceDto acceptExchanceDto) {
        BootcoinTransaction bootcoinTransactionBD = service.findById(acceptExchanceDto.getIdTransaction());
        if (bootcoinTransactionBD == null) {
            return ResponseEntity.notFound().build();
        }

        bootcoinTransactionBD.setStatus("FINISHED");
        bootcoinTransactionBD.setUpdatedAt(LocalDateTime.now());

        return (service.makeTranaction(bootcoinTransactionBD))
                ? ResponseEntity.ok().body(service.update(bootcoinTransactionBD))
                : ResponseEntity.badRequest().build();
    }

}
