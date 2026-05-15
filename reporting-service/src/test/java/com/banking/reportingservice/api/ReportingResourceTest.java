package com.banking.reportingservice.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ReportingResourceTest {

    @Test
    void shouldReturnHealthUp() {
        given()
            .when().get("/api/v1/reporting/health")
            .then()
            .statusCode(200)
            .body("status", is("UP"));
    }
}
