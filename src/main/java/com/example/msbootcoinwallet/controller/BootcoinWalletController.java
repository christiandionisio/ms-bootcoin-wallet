package com.example.msbootcoinwallet.controller;

import com.example.msbootcoinwallet.model.BootcoinWallet;
import com.example.msbootcoinwallet.service.BootcoinWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bootcoin-wallet")
public class BootcoinWalletController {

    @Autowired
    private BootcoinWalletService service;

    @GetMapping
    public ResponseEntity<List<BootcoinWallet>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BootcoinWallet> findById(@PathVariable String id) {
        BootcoinWallet bootcoinWalletBD = service.findById(id);
        return (bootcoinWalletBD == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(bootcoinWalletBD);
    }

    @PostMapping
    public ResponseEntity<BootcoinWallet> save(@RequestBody BootcoinWallet bootcoinWallet) {
        bootcoinWallet.setCreatedAt(LocalDateTime.now());
        bootcoinWallet.setUpdatedAt(LocalDateTime.now());
        BootcoinWallet bootcoinWalletBD = service.save(bootcoinWallet);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bootcoinWallet.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
