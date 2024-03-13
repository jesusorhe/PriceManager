package com.company.pricemanager.domain.models;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class Price {
    private final @NonNull Long brandId;
    private final @NonNull Long productId;
    private final @NonNull Integer priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final @NonNull Integer priority;
    private final @NonNull BigDecimal price;
    private final @NonNull String currency;

    public static Price of(Long brandId, Long productId, Integer priceList, LocalDateTime startDate,
                           LocalDateTime endDate, Integer priority, BigDecimal price, String currency) {
        return new Price(brandId, productId, priceList, startDate, endDate, priority, price, currency);
    }
    private Price(@NonNull Long brandId, @NonNull Long productId, @NonNull Integer priceList, LocalDateTime startDate,
                  LocalDateTime endDate, @NonNull Integer priority, @NonNull BigDecimal price, @NonNull String currency) {
        this.brandId = Objects.requireNonNull(brandId, "BrandId cannot be null");
        this.productId = Objects.requireNonNull(productId, "BrandId cannot be null");
        this.priceList = Objects.requireNonNull(priceList, "PriceList cannot be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = Objects.requireNonNull(priority, "Priority cannot be null");
        this.price = Objects.requireNonNull(price, "Price cannot be null");
        this.currency = Objects.requireNonNull(currency, "Currency cannot be null");
    }
}
