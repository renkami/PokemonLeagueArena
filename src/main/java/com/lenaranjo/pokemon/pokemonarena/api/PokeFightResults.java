package com.lenaranjo.pokemon.pokemonarena.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
class PokeFightResults {
    @JsonProperty("pokefight_result")
    private List<String> pokefightResult;
}
