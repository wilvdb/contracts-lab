package com.bil.contract.donuts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(ids = {"com.bil.contract:donuts-factory:+:stubs:6565"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ContractTest {

    @Autowired
    MockMvc mockMvc;

    public JacksonTester<Donut> json;

    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMappper);
    }

    @Test
    public void shouldProcessCreateDonuts() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/donuts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(new Donut(120, 150, 200)).getJson()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
