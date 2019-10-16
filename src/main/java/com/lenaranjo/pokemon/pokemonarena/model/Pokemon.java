package com.lenaranjo.pokemon.pokemonarena.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lenaranjo.pokemon.pokemonarena.model.Types;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon implements Comparable<Pokemon> {
	private int id;
	private String name;
	private String url;
	private Sprites sprites;
	private List<Types> types;
	private String weight;
	private String height;

	@Override
	public int compareTo(Pokemon o) {
		return this.id - o.getId();
	}

	public final void setIdFromUrl() {
		String[] parts = url.split("/");
		this.id = Integer.parseInt(parts[parts.length - 1]);
	}
}
