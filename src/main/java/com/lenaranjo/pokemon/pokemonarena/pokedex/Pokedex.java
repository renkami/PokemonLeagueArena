package com.lenaranjo.pokemon.pokemonarena.pokedex;

import java.util.List;

import com.lenaranjo.pokemon.pokemonarena.model.Pokemon;
import com.lenaranjo.pokemon.pokemonarena.model.PokemonBase;

public interface Pokedex {
	Pokemon getPokemon(final String name);

	PokemonBase getPokemonBase(final String name);

	List<PokemonBase> getPokemonList();
}
