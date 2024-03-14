package com.company.pricemanager.infrastructure.repositories;

import com.company.pricemanager.domain.PriceMother;
import com.company.pricemanager.infraestructure.repositories.JpaPriceRepository;
import com.company.pricemanager.infraestructure.repositories.PriceRepositoryAdapter;
import com.company.pricemanager.infrastructure.entities.PriceEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.company.pricemanager.domain.PriceMother.brandId;
import static com.company.pricemanager.domain.PriceMother.productId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryAdapterTest {
    @Mock
    JpaPriceRepository jpaPriceRepository;
    PriceRepositoryAdapter priceRepositoryAdapter;

    @BeforeEach
    void initPriceRepositoryAdapter() {
        this.priceRepositoryAdapter = new PriceRepositoryAdapter(jpaPriceRepository);
    }

    @Test
    void testFindByBrandIdAndProductIdSuccess() {
        // GIVEN
        var priceEntity = PriceEntityMother.createWithValues();
        var price = PriceMother.createWithValues();
        when(jpaPriceRepository.findByBrandIdAndProductId(any(Long.class), any(Long.class)))
                .thenReturn(Optional.of(List.of(priceEntity)));
        // WHEN
        var optionalPriceList = this.priceRepositoryAdapter.findBy(brandId, productId);
        // THEN
        assertTrue(optionalPriceList.isPresent());
        var priceList = optionalPriceList.get();
        assertFalse(priceList.isEmpty());
        var priceFromEntity = priceList.get(0);
        assertEquals(price.getBrandId(), priceFromEntity.getBrandId());
        assertEquals(price.getProductId(), priceFromEntity.getProductId());
        assertEquals(price.getPriceList(), priceFromEntity.getPriceList());
        assertEquals(price.getStartDate(), priceFromEntity.getStartDate());
        assertEquals(price.getEndDate(), priceFromEntity.getEndDate());
        assertEquals(price.getPriority(), priceFromEntity.getPriority());
        assertEquals(price.getPrice(), priceFromEntity.getPrice());
        assertEquals(price.getCurrency(), priceFromEntity.getCurrency());
    }

    @Test
    void testFindByBrandIdAndProductIdEmptySuccess() {
        // GIVEN
        when(jpaPriceRepository.findByBrandIdAndProductId(any(Long.class), any(Long.class)))
                .thenReturn(Optional.of(List.of()));
        // WHEN
        var optionalPriceList = this.priceRepositoryAdapter.findBy(brandId, productId);
        // THEN
        assertTrue(optionalPriceList.isEmpty());
    }
}
