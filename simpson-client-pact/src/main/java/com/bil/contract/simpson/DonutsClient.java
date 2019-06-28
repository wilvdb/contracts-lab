package com.bil.contract.simpson;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface DonutsClient {

    @RequestLine("POST /donuts")
    @Headers("Content-Type: application/json")
    Response createDonuts(Donut donut);

    @RequestLine("GET /donuts")
    Response getDonuts();

    @RequestLine("GET /donuts/{id}")
    Donut getDonut(@Param("id") long id);
}
