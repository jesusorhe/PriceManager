package com.company.pricemanager.domain.ports.out;

import com.company.pricemanager.domain.models.Price;

import java.util.List;
import java.util.Optional;

public interface PriceRepository {
    Optional<List<Price>> findBy(Long brandId, Long productId);
}
