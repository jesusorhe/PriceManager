package com.company.pricemanager.infraestructure.repositories;

import com.company.pricemanager.infraestructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {
    Optional<List<PriceEntity>> findByBrandIdAndProductId(Long brandId, Long ProductId);
}
