package com.bil.contract.donuts;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.net.URI;
import java.util.List;

public interface DonutsClient {

    @RequestLine("POST /donuts")
    @Headers("Content-Type: application/json")
    Response createDonuts(Donut donut);

    @RequestLine("GET /donuts")
    List<Donut> getDonuts();

    @RequestLine("GET /donuts/{id}")
    Donut getDonut(@Param("id") long id);
}
