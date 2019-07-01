package com.bil.contract.donuts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public abstract class Donuts_factory_pactContractsBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new DonutsController());
    }
}
