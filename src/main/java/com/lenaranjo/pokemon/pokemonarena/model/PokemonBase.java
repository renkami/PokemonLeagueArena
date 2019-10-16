package com.lenaranjo.pokemon.pokemonarena.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonBase implements Comparable<PokemonBase>, Cloneable {
	private int id;
	private String name;
	private String url;
	private int position;

	@Override
	public int compareTo(PokemonBase o) {
		return this.id - o.getId();
	}

	public final void setIdFromUrl() {
		String[] parts = url.split("/");
		this.id = Integer.parseInt(parts[parts.length - 1]);
	}

	public PokemonBase clonar() {
		try {
			return (PokemonBase) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
