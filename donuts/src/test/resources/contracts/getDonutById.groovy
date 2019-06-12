package contracts

org.springframework.cloud.contract.spec.Contract.make {
    description("""
        Donuts API
""")
    label("Get donut by id")
    request {
        method GET()

        url('/donuts') {

            queryParameters {
                parameter 'id' : $(anyInteger())
            }
        }
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body(
                  sugar: $(anyInteger()) ,
                  flour: $(anyInteger()) ,
                  butter: $(anyInteger())
              )
        headers {
            contentType(applicationJson())
        }
    }
}
