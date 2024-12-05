package com.abdo.hunter.web.vm.response;

import com.abdo.hunter.domain.enums.Difficulty;
import com.abdo.hunter.domain.enums.SpeciesType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SpeciesGetResponse {
    private UUID id;
    private String name;
    private SpeciesType category;
    private Double minimumWeight;
    private Difficulty difficulty;
    private Integer points;
}
