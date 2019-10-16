package com.lenaranjo.pokemon.pokemonarena.pokedex;

import com.lenaranjo.pokemon.pokemonarena.model.Envelope;
import com.lenaranjo.pokemon.pokemonarena.model.Pokemon;
import com.lenaranjo.pokemon.pokemonarena.model.PokemonBase;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PokeApiClient implements Pokedex {
	private final Map<String, Pokemon> cache = new ConcurrentHashMap<>();
	private final WebTarget client;

	public PokeApiClient() {
		this(ClientBuilder.newClient().target("https://pokeapi.co").path("api").path("v2").path("pokemon"));
	}

	// For testing only
	PokeApiClient(WebTarget client) {
		this.client = client;
	}

	@Override
	public Pokemon getPokemon(final String name) {
		return cache.computeIfAbsent(name, this::getPokemonFromApi);
	}

	private Pokemon getPokemonFromApi(final String name) {
		return client.path(name).request(MediaType.APPLICATION_JSON).get(Pokemon.class);
	}

	public List<PokemonBase> getPokemonList() {
		final Envelope env = client.queryParam("limit", "9999").request(MediaType.APPLICATION_JSON).get(Envelope.class);
		env.getResults().stream().peek(PokemonBase::setIdFromUrl)
				.collect(Collectors.toMap(PokemonBase::getName, Function.identity()));
		return env.getResults();

	}

	@Override
	public PokemonBase getPokemonBase(String name) {
		return getPokemonList().stream().filter(pokemon -> name.equals(pokemon.getName())).findAny().orElse(null);
	}

}
