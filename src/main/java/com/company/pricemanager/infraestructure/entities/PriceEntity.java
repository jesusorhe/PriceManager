package com.company.pricemanager.infraestructure.entities;

import com.company.pricemanager.domain.models.Price;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "PRICES")
public class PriceEntity {
    @Id
    private UUID id;
    private Long brandId;
    private Long productId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private BigDecimal price;
    private String currency;

    public Price toPrice() {
        return new Price(brandId, productId, priceList, startDate, endDate, priority, price, currency);
    }
}
