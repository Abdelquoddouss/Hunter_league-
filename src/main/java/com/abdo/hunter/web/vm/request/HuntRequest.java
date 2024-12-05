package com.abdo.hunter.web.vm.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
public class HuntRequest {

    @NotNull(message = "you must enter the participation id")
    private UUID participationId;

    @NotNull(message = "you must enter the species id")
    private UUID speciesId;

    @NotNull(message = "you must enter the weight")
    @Positive
    private Double weight;


}
