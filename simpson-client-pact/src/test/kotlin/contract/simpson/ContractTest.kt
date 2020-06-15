package contract.simpson

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonArray
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import com.bil.contract.simpson.Donut
import com.bil.contract.simpson.DonutsClient
import feign.Feign
import feign.gson.GsonEncoder
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(PactConsumerTestExt::class)
class ContractTest {

    @Pact(provider = "donuts-factory-pact", consumer = "simpson-pact")
    fun `create donuts`(builder: PactDslWithProvider): RequestResponsePact {
        val donutsBody = PactDslJsonArray().minArrayLike(1)
                .integerType("butter")
                .integerType("sugar")
                .integerType("flour")
                .closeObject()

        val donutBody = PactDslJsonBody()
                .integerType("butter")
                .integerType("sugar")
                .integerType("flour")

        return builder
                .given("creating successfully a donut")
                .uponReceiving("all ingredients for a donut")
                    .path("/donuts")
                    .method("POST")
                    .headers("Content-Type", "application/json")
                    .body(donutBody)
                .willRespondWith()
                    .status(201)
                .given("failing to create a donut")
                .uponReceiving("some missing ingredients")
                    .path("/donuts")
                    .method("POST")
                    .headers("Content-Type", "application/json")
                    .body("{\"butter\":-100, \"sugar\": 150, \"flour\": 200}")
                .willRespondWith()
                    .status(400)
                .given("getting all donuts")
                .uponReceiving("every produced donuts by the factory")
                    .path("/donuts")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(donutsBody)
                    .headers(mapOf("Content-Type" to "application/json"))
                .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "create donuts")
    fun `check create donuts`(mockServer: MockServer) {
        val donutsClient = Feign.builder()
                .encoder(GsonEncoder())
                .target(DonutsClient::class.java, mockServer.getUrl())

        assertEquals(201, donutsClient.createDonuts(Donut(120, 150, 200)).status())

        assertEquals(400, donutsClient.createDonuts(Donut(-100, 150, 200)).status())

        assertEquals(200, donutsClient.getDonuts().status())

    }
}
