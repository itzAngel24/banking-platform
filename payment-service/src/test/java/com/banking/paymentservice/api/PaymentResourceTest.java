package com.banking.paymentservice.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class PaymentResourceTest {

    @Test
    void shouldReturnHealthUp() {
        given()
            .when().get("/api/v1/payment/health")
            .then()
            .statusCode(200)
            .body("status", is("UP"));
    }
}
