package com.bil.contract.simpson;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import static io.pactfoundation.consumer.dsl.LambdaDsl.*;
import au.com.dius.pact.model.RequestResponsePact;
import feign.Feign;
import feign.Response;
import feign.gson.GsonEncoder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ContractTest {


    DonutsClient donutsClient = Feign.builder().encoder(new GsonEncoder())
            .target(DonutsClient.class, "http://localhost:8085");

    @Rule
    public PactProviderRuleMk2 mockProvider =
            new PactProviderRuleMk2("donuts-factory-pact", "localhost", 8085, this);


    @Pact(consumer="simpson-pact")
    public RequestResponsePact donutsOk(PactDslWithProvider builder) throws Exception {
        DslPart arrayOfDonuts = newJsonArray((a) -> a.object((o) -> {
            o.numberType("butter", "sugar", "flour");
        })).build();

        DslPart anyDonuts = newJsonBody((b) -> b.numberType("butter", "sugar", "flour")).build();
        DslPart invalidDonuts = newJsonBody((b) -> {
            b.numberType("sugar", "flour");
            b.numberType("butter", -100);
        }).build();

        return builder
                .given("creating successfully a donut")
                    .uponReceiving("all ingredients for a donut")
                    .path("/donuts")
                    .method("POST")
                    .headers("Content-Type", "application/json")
                    .body(anyDonuts)
                    .willRespondWith()
                    .status(201)
                .given("getting all donuts")
                    .uponReceiving("every produced donuts by the factory")
                    .path("/donuts")
                    .method("GET")
                    .willRespondWith()
                    .status(200)
                    .body(arrayOfDonuts)
                    .headers(responseHeaders())
                
                .toPact();
    }

    @Test
    @PactVerification
    public void runDonuts() {
        Response response = this.donutsClient.createDonuts(new Donut(120, 150, 200));
        assertEquals(response.status(), 201);

        response = this.donutsClient.getDonuts();
        Assert.assertEquals(response.status(), 200);

    }

    private Map<String, String> responseHeaders() {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        return map;
    }

}