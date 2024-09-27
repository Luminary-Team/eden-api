package com.luminary.apieden.controller.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MapExample {
    @Schema(name = "<fieldToBeUpdated>", description = "Put the field to be updated as the 'key', and the new value as 'value'")
    private String fieldToBeUpdated;
}
