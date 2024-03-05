package com.company.pricemanager.domain.ports.in;

import com.company.pricemanager.domain.models.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RetrievePriceUseCase {

    Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime date);
}
