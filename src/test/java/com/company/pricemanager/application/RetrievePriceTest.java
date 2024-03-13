package com.company.pricemanager.application;

import com.company.pricemanager.application.usecases.RetrievePriceUseCaseImpl;
import com.company.pricemanager.domain.PriceMother;
import com.company.pricemanager.domain.models.Price;
import com.company.pricemanager.domain.ports.in.RetrievePriceUseCase;
import static com.company.pricemanager.domain.PriceMother.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.company.pricemanager.domain.ports.out.PriceRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RetrievePriceTest {
    private final LocalDateTime date = LocalDateTime.of(2020, Month.JUNE, 14, 10,0,0);
    @Mock
    PriceRepository priceRepository;
    RetrievePriceUseCase retrievePriceUseCase;
    @BeforeEach
    void initRetrievePriceUseCase() {
        this.retrievePriceUseCase = new RetrievePriceUseCaseImpl(priceRepository);
    }
    @Test
    void testRetrievePriceSuccess() {
        // GIVEN
        var basicPrice = PriceMother.createWithValues();
        when(priceRepository.findBy(any(Long.class), any(Long.class)))
                .thenReturn(Optional.of(List.of(basicPrice)));
        // WHEN
        var retrievedOptionalPrice = this.retrievePriceUseCase.getPrice(brandId, productId, date);
        // THEN
        assertTrue(retrievedOptionalPrice.isPresent());
        var retreivedPrice = retrievedOptionalPrice.get();
        assertEquals(retreivedPrice.getBrandId(), basicPrice.getBrandId());
        assertEquals(retreivedPrice.getProductId(), basicPrice.getProductId());
        assertEquals(retreivedPrice.getPriceList(), basicPrice.getPriceList());
        assertEquals(retreivedPrice.getStartDate(), basicPrice.getStartDate());
        assertEquals(retreivedPrice.getEndDate(), basicPrice.getEndDate());
        assertEquals(retreivedPrice.getPriority(), basicPrice.getPriority());
        assertEquals(retreivedPrice.getPrice(), basicPrice.getPrice());
        assertEquals(retreivedPrice.getCurrency(), basicPrice.getCurrency());
    }

    @Test
    void testRetrievePriceEmptySuccess() {
        // GIVEN
        when(priceRepository.findBy(any(Long.class), any(Long.class)))
                .thenReturn(Optional.empty());
        // WHEN
        Optional<Price> retrievedPrice = this.retrievePriceUseCase.getPrice(brandId, productId, date);
        // THEN
        assertTrue(retrievedPrice.isEmpty());
    }
}
