package com.bil.petcliniccontract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class FraudBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController()

        );
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}
