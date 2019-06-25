package contracts

org.springframework.cloud.contract.spec.Contract.make {
    description("""
        Donuts API
""")
    label("Get all donuts")
    request {
        method GET()
        url '/donuts'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body(
                  sugar: $(regex('[0-9]+')) ,
                  flour: $(regex('[0-9]+')) ,
                  butter: $(regex('[0-9]+'))
              )
        headers {
            contentType(applicationJson())
        }
    }
}
