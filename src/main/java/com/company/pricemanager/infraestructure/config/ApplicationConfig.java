package com.company.pricemanager.infraestructure.config;

import com.company.pricemanager.application.usecases.RetrievePriceUseCaseImpl;
import com.company.pricemanager.domain.ports.in.RetrievePriceUseCase;
import com.company.pricemanager.domain.ports.out.PriceRepository;
import com.company.pricemanager.infraestructure.repositories.JpaPriceRepository;
import com.company.pricemanager.infraestructure.repositories.PriceRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public PriceRepositoryAdapter priceRepositoryImpl(JpaPriceRepository jpaPriceRepository) {
        return new PriceRepositoryAdapter(jpaPriceRepository);
    }
    @Bean
    public PriceRepository priceRepository(PriceRepositoryAdapter priceRepositoryImpl) {
        return priceRepositoryImpl;
    }
    @Bean
    public RetrievePriceUseCase retrievePriceUseCase(PriceRepository priceRepository) {
        return new RetrievePriceUseCaseImpl(priceRepository);
    }
}
