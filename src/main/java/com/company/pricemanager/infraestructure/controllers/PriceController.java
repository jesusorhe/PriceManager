package com.company.pricemanager.infraestructure.controllers;

import com.company.pricemanager.domain.ports.in.RetrievePriceUseCase;
import com.company.pricemanager.infraestructure.entities.PriceResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/price")
public class PriceController {

    private final RetrievePriceUseCase retrievePriceUseCase;

    public PriceController(RetrievePriceUseCase retrievePriceUseCase) {
        this.retrievePriceUseCase = retrievePriceUseCase;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(@Param("brandId") Long brandId,
                                                  @Param("productId") Long productId,
                                                  @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        var optionalPrice = retrievePriceUseCase.getPrice(brandId, productId, date);
        if (optionalPrice.isPresent()) {
            var priceResponse = PriceResponse.from(optionalPrice.get());
            return ResponseEntity.ok(priceResponse);
        }
        throw new NoSuchElementException();
    }
}
