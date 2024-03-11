package com.company.pricemanager.application.usecases;

import com.company.pricemanager.domain.models.Price;
import com.company.pricemanager.domain.ports.in.RetrievePriceUseCase;
import com.company.pricemanager.domain.ports.out.PriceRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
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
        var prices = optionalPrices.get();
        log.debug("PriceList retrieved from repository: {}", prices);
        return getPriorityPriceBy(prices, date);
    }

    private Optional<Price> getPriorityPriceBy(List<Price> prices, LocalDateTime date) {
        return prices.stream().filter(price -> isDateBetween(date, price.getStartDate(), price.getEndDate()))
                .max(Comparator.comparing(Price::getPriority));
    }

    private boolean isDateBetween(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
        return (startDate == null || date.isAfter(startDate))
                && (endDate == null || date.isBefore(endDate));
    }
}
