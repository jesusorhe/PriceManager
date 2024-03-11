package com.company.pricemanager.infraestructure.repositories;

import com.company.pricemanager.domain.models.Price;
import com.company.pricemanager.domain.ports.out.PriceRepository;
import com.company.pricemanager.infraestructure.entities.PriceEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
        log.debug("PriceEntityList retrieved from db with findBy brandId and productId: {}", priceEntityList);
        return Optional.of(priceEntityList.get().stream().map(PriceEntity::toPrice).collect(Collectors.toList()));
    }
}
