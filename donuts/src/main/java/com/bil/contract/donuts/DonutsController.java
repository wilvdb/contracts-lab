package com.bil.contract.donuts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequestMapping("/donuts")
public class DonutsController {

    @PostMapping(consumes = "application/json")
    public ResponseEntity createDonuts() {
            return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity getDonuts() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity getDonut(@PathParam("id") long id) {
        return ResponseEntity.ok().build();
    }

}
