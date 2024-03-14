package com.company.pricemanager.infrastructure.restIT;

import com.company.pricemanager.infraestructure.entities.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceManagerIT {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testGetPrice1() {
        baseTestGetPrice(1, 35455, "2020-06-14T10:00:00", 1);
    }

    @Test
    void testGetPrice2() {
        baseTestGetPrice(1, 35455, "2020-06-14T16:00:00", 2);
    }

    @Test
    void testGetPrice3() {
        baseTestGetPrice(1, 35455, "2020-06-14T21:00:00", 1);
    }

    @Test
    void testGetPrice4() {
        baseTestGetPrice(1, 35455, "2020-06-15T10:00:00", 3);
    }

    @Test
    void testGetPrice5() {
        baseTestGetPrice(1, 35455, "2020-06-16T21:00:00", 4);
    }

    private void baseTestGetPrice(int brandId, int productId, String date, int priceListExpected) {
        log.info("--- Init Test GetPrice with brandId: {}, productId: {} and date: {} ---",
                brandId, productId, date);
        // GIVEN
        var url = "/api/price?brandId={brandId}&productId={productId}&date={date}";
        var params = new HashMap<String, Object>();
        params.put("brandId", brandId);
        params.put("productId", productId);
        params.put("date", date);
        // WHEN
        var response = testRestTemplate.getForEntity(url, PriceResponse.class, params);
        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        var responseBody = response.getBody();
        log.info(String.valueOf(responseBody));
        assertNotNull(responseBody);
        assertEquals(responseBody.getPriceList(), priceListExpected);
        log.info("--- Finish Test GetPrice ---");
    }
}
