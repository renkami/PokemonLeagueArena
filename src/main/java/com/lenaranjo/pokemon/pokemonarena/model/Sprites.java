package com.lenaranjo.pokemon.pokemonarena.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Sprites {
	private String backDefault;
	private String backFemale;
	private String backShiny;
	private String backShinyFemale;
	private String frontDefault;
	private String frontFemale;
	private String frontShiny;
	private String frontShinyFemale;
}
