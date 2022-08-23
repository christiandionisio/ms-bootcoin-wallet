package com.example.msbootcoinwallet.controller;

import com.example.msbootcoinwallet.dto.BuyAndSellRateDto;
import com.example.msbootcoinwallet.model.CoinConfiguration;
import com.example.msbootcoinwallet.service.CoinConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/coin-configurations")
public class CoinConfigurationController {

    @Autowired
    private CoinConfigurationService service;

    @GetMapping()
    public ResponseEntity<List<CoinConfiguration>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoinConfiguration> findById(@PathVariable String id) {
        CoinConfiguration coinConfigurationBD = service.findbyId(id);
        return (coinConfigurationBD == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(coinConfigurationBD);
    }

    @PostMapping
    public ResponseEntity<CoinConfiguration> save(@RequestBody CoinConfiguration coinConfiguration) {
        coinConfiguration.setCreatedAt(LocalDateTime.now());
        coinConfiguration.setUpdatedAt(LocalDateTime.now());
        CoinConfiguration coinConfigurationSaved = service.save(coinConfiguration);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(coinConfiguration.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<CoinConfiguration> updateBuyAndSellRate(@RequestBody BuyAndSellRateDto buyAndSellRateDto) {
        CoinConfiguration coinConfigurationBD = service.findbyId(buyAndSellRateDto.getIdCoin());
        if (coinConfigurationBD == null) {
            return ResponseEntity.notFound().build();
        }

        coinConfigurationBD.setPurchasePrice(buyAndSellRateDto.getPurchasePrice());
        coinConfigurationBD.setSalePrice(buyAndSellRateDto.getSalePrice());
        coinConfigurationBD.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok().body(service.update(coinConfigurationBD));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<CoinConfiguration> findByCoinName(@PathVariable String name) {
        CoinConfiguration coinConfigurationBD = service.findByCoinName(name);
        return (coinConfigurationBD == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(coinConfigurationBD);
    }
}
