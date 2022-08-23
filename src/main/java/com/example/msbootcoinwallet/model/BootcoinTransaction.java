package com.example.msbootcoinwallet.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RedisHash("bootcoin-transaction")
@Data
@NoArgsConstructor
public class BootcoinTransaction {

    @Id
    private String id;
    private BigDecimal amountCoin;
    private String phoneBuyer;
    private String phoneSeller;
    private String paymentMode;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
