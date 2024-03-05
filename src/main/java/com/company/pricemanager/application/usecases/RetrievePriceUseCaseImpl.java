package com.company.pricemanager.application.usecases;

import com.company.pricemanager.domain.models.Price;
import com.company.pricemanager.domain.ports.in.RetrievePriceUseCase;
import com.company.pricemanager.domain.ports.out.PriceRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RetrievePriceUseCaseImpl implements RetrievePriceUseCase {

    private final PriceRepository priceRepository;

    public RetrievePriceUseCaseImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime date) {
        Optional<List<Price>> optionalPrices = priceRepository.findBy(brandId, productId);
        if (optionalPrices.isEmpty() || optionalPrices.get().isEmpty()) {
            return Optional.empty();
        }
        return getPriorityPriceBy(optionalPrices.get(), date);
    }

    private Optional<Price> getPriorityPriceBy(List<Price> prices, LocalDateTime date) {
        return prices.stream().filter(price -> date.isAfter(price.getStartDate()) && date.isBefore(price.getEndDate()))
                .max(Comparator.comparing(Price::getPriority));
    }
}
