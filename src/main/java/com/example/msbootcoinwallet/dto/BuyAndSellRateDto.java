package com.example.msbootcoinwallet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BuyAndSellRateDto {

    private String idCoin;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;

}
