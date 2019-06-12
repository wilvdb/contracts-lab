package contracts

org.springframework.cloud.contract.spec.Contract.make {
    description("""
        Donuts API
""")
    label("Create donut")
    request {
        method POST()
        url '/donuts'
        body(
                sugar: $(anyInteger()),
                flour: $(anyInteger()),
                butter: $(anyInteger())
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status CREATED()
    }
}