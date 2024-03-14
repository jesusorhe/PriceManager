package com.company.pricemanager.infraestructure.controllers;

import com.company.pricemanager.domain.ports.in.RetrievePriceUseCase;
import com.company.pricemanager.infraestructure.entities.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/price")
public class PriceController {

    private final RetrievePriceUseCase retrievePriceUseCase;

    public PriceController(RetrievePriceUseCase retrievePriceUseCase) {
        this.retrievePriceUseCase = retrievePriceUseCase;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(@RequestParam("brandId") Long brandId,
                                                  @RequestParam("productId") Long productId,
                                                  @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        log.info("Request getPrice to retrieve price by brandId {}, productId {} and date {}", brandId, productId, date);
        var optionalPrice = retrievePriceUseCase.getPrice(brandId, productId, date);
        if (optionalPrice.isPresent()) {
            var priceResponse = PriceResponse.from(optionalPrice.get());
            log.info("Response getPrice retrieved price: {}", priceResponse);
            return ResponseEntity.ok(priceResponse);
        }
        log.info(String.format("There is no element with brandId %s, productId %s and date %s", brandId, productId, date));
        return ResponseEntity.noContent().build();
    }
}
