package com.abdo.hunter.web.vm.request;

import com.abdo.hunter.domain.enums.Difficulty;
import com.abdo.hunter.domain.enums.SpeciesType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeciesRequest {

    @NotNull(message = "you must enter the name of the species")
    private String name;

    @NotNull(message = "you must enter the category of the species")
    private SpeciesType category;

    @NotNull(message = "you must enter the minimumWeight of the species")
    private Double minimumWeight;

    @NotNull(message = "you must enter the difficulty of the species")
    private Difficulty difficulty;

    @NotNull(message = "you must enter the points of the species")
    private Integer points;
}
