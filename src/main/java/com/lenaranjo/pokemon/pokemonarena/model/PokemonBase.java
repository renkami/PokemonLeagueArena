package com.lenaranjo.pokemon.pokemonarena.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonBase implements Comparable<PokemonBase>{
    private int id;
    private String name;
    private String url;

    @Override
    public int compareTo(PokemonBase o) {
        return this.id - o.getId();
    }

    public final void setIdFromUrl() {
        String[] parts = url.split("/");
        this.id = Integer.parseInt(parts[parts.length - 1]);
    }
}
