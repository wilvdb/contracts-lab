package com.bil.contract.donuts;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import feign.Feign;
import feign.Response;
import feign.gson.GsonEncoder;
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
            new PactProviderRuleMk2("donuts-api-pact", "localhost", 8085, this);


    @Pact(consumer="donuts-factory-pact")
    public RequestResponsePact donutsOk(PactDslWithProvider builder) throws Exception {
        return builder
                .given("")
                .uponReceiving("Represents a successful scenario of creating a donut")
                .path("/donuts")
                .method("POST")
                .headers("Content-Type", "application/json")
                .body("{\"butter\": 120, \"sugar\": 150, \"flour\": 200}")
                .willRespondWith()
                .status(201)
                .headers(responseHeaders())
                .given("")
                .uponReceiving("Represents a successful scenario of getting all donuts")
                .path("/donuts")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("[{\"butter\": 120, \"sugar\": 150, \"flour\": 200}]")
                .headers(responseHeaders())
                .toPact();
    }

    @Test
    @PactVerification
    public void runDonuts() {
        Response response = this.donutsClient.createDonuts(new Donut(120, 150, 200));
        assertEquals(response.status(), 201);

        response = this.donutsClient.getDonuts();
        assertEquals(response.status(), 200);

    }

    private Map<String, String> responseHeaders() {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json;charset=UTF-8");
        return map;
    }

}