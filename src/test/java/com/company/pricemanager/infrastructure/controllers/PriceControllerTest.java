package com.company.pricemanager.infrastructure.controllers;

import com.company.pricemanager.domain.PriceMother;
import com.company.pricemanager.domain.ports.in.RetrievePriceUseCase;
import com.company.pricemanager.infraestructure.controllers.PriceController;
import com.company.pricemanager.infrastructure.entities.PriceResponseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static com.company.pricemanager.domain.PriceMother.brandId;
import static com.company.pricemanager.domain.PriceMother.productId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {
    private final LocalDateTime date = LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0);
    @Mock
    RetrievePriceUseCase retrievePriceUseCase;

    PriceController priceController;

    @BeforeEach
    void initPriceController() {
        this.priceController = new PriceController(retrievePriceUseCase);
    }

    @Test
    void testGetPriceSuccess() {
        // GIVEN
        var priceResponse = PriceResponseMother.createWithValues();
        var price = PriceMother.createWithValues();
        when(retrievePriceUseCase.getPrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(price));
        // WHEN
        var responseEntityPriceResponse = this.priceController.getPrice(brandId, productId, date);
        // THEN
        assertEquals(HttpStatus.OK, responseEntityPriceResponse.getStatusCode());
        var priceResponseFromController = responseEntityPriceResponse.getBody();
        assertNotNull(priceResponseFromController);
        assertEquals(priceResponseFromController.getBrandId(), priceResponse.getBrandId());
        assertEquals(priceResponseFromController.getProductId(), priceResponse.getProductId());
        assertEquals(priceResponseFromController.getPriceList(), priceResponse.getPriceList());
        assertEquals(priceResponseFromController.getStartDate(), priceResponse.getStartDate());
        assertEquals(priceResponseFromController.getEndDate(), priceResponse.getEndDate());
        assertEquals(priceResponseFromController.getPrice(), priceResponse.getPrice());
    }

    @Test
    void testGetPriceEmptySuccess() {
        // GIVEN
        var priceResponse = PriceResponseMother.createWithValues();
        var price = PriceMother.createWithValues();
        when(retrievePriceUseCase.getPrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());
        // WHEN
        var responseEntityPriceResponse = this.priceController.getPrice(brandId, productId, date);
        // THEN
        assertEquals(HttpStatus.NO_CONTENT, responseEntityPriceResponse.getStatusCode());
        var priceResponseFromController = responseEntityPriceResponse.getBody();
        assertNull(priceResponseFromController);
    }
}
