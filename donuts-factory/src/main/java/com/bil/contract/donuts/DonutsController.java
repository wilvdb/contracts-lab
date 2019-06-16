package com.bil.contract.donuts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.Arrays;

@RestController
public class DonutsController {

    @PostMapping(value = "/donuts", consumes = "application/json")
    public ResponseEntity createDonuts(@RequestBody  Donut donut) {
            return ResponseEntity.created(URI.create("http://localhost:8080/donuts/1")).build();
    }

    @GetMapping(value = "/donuts", produces = "application/json")
    public ResponseEntity getDonuts() {
        return ResponseEntity.ok(Arrays.asList(new Donut(120, 150, 140)));
    }

    @GetMapping(value = "/donut/{id}", produces = "application/json")
    public ResponseEntity getDonut(@PathParam("id") long id) {
        return ResponseEntity.ok(new Donut(120, 150, 140));
    }

}
