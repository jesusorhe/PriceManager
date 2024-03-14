package com.company.pricemanager.infraestructure.entities;

import com.company.pricemanager.domain.models.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class PriceResponse {
    private Long brandId;
    private Long productId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public static PriceResponse from(Price price) {
        return new PriceResponse(price.getBrandId(), price.getProductId(), price.getPriceList(), price.getStartDate(),
                price.getEndDate(), price.getPrice());
    }
}
