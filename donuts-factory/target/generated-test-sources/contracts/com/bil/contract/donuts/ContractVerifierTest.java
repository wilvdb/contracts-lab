package com.bil.contract.donuts;

import com.bil.contract.donuts.ContractVerifierBase;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;

public class ContractVerifierTest extends ContractVerifierBase {

	@Test
	public void validate_allDonuts() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json");

		// when:
			ResponseOptions response = given().spec(request)
					.get("/donuts");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['flour']").matches("[0-9]+");
			assertThatJson(parsedJson).field("['sugar']").matches("[0-9]+");
			assertThatJson(parsedJson).field("['butter']").matches("[0-9]+");
	}

	@Test
	public void validate_createDonut() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"sugar\":1864698785,\"flour\":-1208929909,\"butter\":302813980}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/donuts");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
	}

	@Test
	public void validate_getDonutById() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json");

		// when:
			ResponseOptions response = given().spec(request)
					.queryParam("id","-466416981")
					.get("/donuts");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['butter']").matches("-?(\\d+)");
			assertThatJson(parsedJson).field("['sugar']").matches("-?(\\d+)");
			assertThatJson(parsedJson).field("['flour']").matches("-?(\\d+)");
	}

}
