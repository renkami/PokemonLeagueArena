package com.lenaranjo.pokemon.pokemonarena.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Types {
	private String slot;

	private Type type;
}