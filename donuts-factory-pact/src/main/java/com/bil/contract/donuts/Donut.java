package com.bil.contract.donuts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donut {

    @Positive
    int butter;
    @Positive
    int sugar;
    @Positive
    int flour;
}
