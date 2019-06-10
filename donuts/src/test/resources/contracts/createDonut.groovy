package contracts

org.springframework.cloud.contract.spec.Contract.make {
    description("""
        Donuts API
""")
    request {
        method POST()
        url '/createDonut'
        body([
                "client.id": $(regex('[0-9]{10}')),
                sugar: 99999
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status CREATED()
        body([
                fraudCheckStatus: "FRAUD",
                "rejection.reason": "Amount too high"
        ])
        headers {
            contentType(applicationJson())
        }
    }
}