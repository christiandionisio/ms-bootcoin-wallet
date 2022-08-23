package com.example.msbootcoinwallet.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RedisHash("bootcoin-wallet")
@Data
@NoArgsConstructor
public class BootcoinWallet {

    @Id
    private String id;
    private String documentType;
    private String documentNumber;
    private String cellphoneNumber;
    private String email;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
