package com.company.pricemanager.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class Price {
    private @NonNull Long brandId;
    private @NonNull Long productId;
    private @NonNull Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private @NonNull Integer priority;
    private @NonNull BigDecimal price;
    private @NonNull String currency;
}
