package contracts

org.springframework.cloud.contract.spec.Contract.make {
    description("""
        Donuts API
""")
    request {
        method GET()
        url '/donuts'
        body([
                "client.id": $(regex('[0-9]{10}')),
                sugar: 99999
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body([
                fraudCheckStatus: "FRAUD",
                "rejection.reason": "Amount too high"
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
