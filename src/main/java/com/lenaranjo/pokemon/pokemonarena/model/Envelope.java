package com.lenaranjo.pokemon.pokemonarena.model;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Envelope {
	private List<PokemonBase> results;

}
