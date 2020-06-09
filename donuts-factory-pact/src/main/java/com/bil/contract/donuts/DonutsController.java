package com.bil.contract.donuts;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;


@RestController
@Validated
public class DonutsController {


    @PostMapping(value = "/donuts", consumes = "application/json")
    public ResponseEntity createDonuts(@RequestBody  @Valid Donut donut) {
            return ResponseEntity.created(URI.create("http://localhost:8080/donuts/1")).build();
    }

    /**
     * TODO complete the implementation
     * @return
     */
    @GetMapping(value = "/donuts", produces = "application/json")
    public ResponseEntity getDonuts() {
        return ResponseEntity.ok(Arrays.asList(new Donut(100, 100, 100)));
    }

    @GetMapping(value = "/donuts/{id}", produces = "application/json")
    public ResponseEntity getDonut(@PathVariable("id") long id) {
        return ResponseEntity.ok(new Donut(120, 150, 200));
    }
}
