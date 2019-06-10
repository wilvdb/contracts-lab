package com.bil.contract.donuts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class DonutsBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new DonutsController()

        );
    }
}
