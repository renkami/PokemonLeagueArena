package com.lenaranjo.pokemon.pokemonarena.api;

import com.lenaranjo.pokemon.pokemonarena.calculator.BattleCalculator;
import com.lenaranjo.pokemon.pokemonarena.model.Pokemon;
import com.lenaranjo.pokemon.pokemonarena.pokedex.PokeApiClient;
import com.lenaranjo.pokemon.pokemonarena.pokedex.Pokedex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PokemonArenaApi {

	private final BattleCalculator battleCalculator = new BattleCalculator();
	private final Pokedex pokedex = new PokeApiClient();
	

	@PostMapping("/tournament")
	public ResponseEntity<?> getBattleCount(@RequestBody PokeFightResults battleResults, Map<String, Object> model) {
		List<Pokemon> fighters;
		try {
			fighters = battleResults.getPokefightResult().stream().map(pokedex::getPokemon)
					.collect(Collectors.toList());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, "No encontramos algún pokemon en la lista"));
		}
		
		try {
			return ResponseEntity.ok().body(battleCalculator.calculateMinimumBattles(fighters));
		} catch (Exception e) {
			model.put("error", e);
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}

	}

	@GetMapping("/pokemon/{name}")
	public Pokemon getPokemonInfo(@PathVariable("name") String name, Map<String, Object> model) {
		model.put("pokemon", pokedex.getPokemon(name));
		return pokedex.getPokemon(name);
	}
	

	/*@GetMapping("/pokemon/{name}")
	public ModelAndView getPokemonInfo(@PathVariable("name") String name, Map<String, Object> model) {
		model.put("pokemon", pokedex.getPokemon(name));
		return new ModelAndView("pokemon", model);
	}*/
}
