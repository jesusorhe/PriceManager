package com.company.pricemanager.infraestructure.repositories;

import com.company.pricemanager.domain.models.Price;
import com.company.pricemanager.domain.ports.out.PriceRepository;
import com.company.pricemanager.infraestructure.entities.PriceEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PriceRepositoryAdapter implements PriceRepository {
    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public Optional<List<Price>> findBy(Long brandId, Long productId) {
        var priceEntityList = jpaPriceRepository.findByBrandIdAndProductId(brandId, productId);
        if (priceEntityList.isEmpty() || priceEntityList.get().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(priceEntityList.get().stream().map(PriceEntity::toPrice).collect(Collectors.toList()));
    }
}
