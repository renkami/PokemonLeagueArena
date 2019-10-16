package com.lenaranjo.pokemon.pokemonarena.calculator;

import com.lenaranjo.pokemon.pokemonarena.model.PokemonBase;

import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BattleCalculator {

	public long calculateMinimumBattles(List<PokemonBase> fighters) throws Exception {
		final List<ResultWrapper> sortedParticipants = IntStream.range(0, fighters.size())
				.mapToObj(index -> new ResultWrapper(index, fighters.get(index)))
				.sorted(Comparator.comparing(ResultWrapper::getPokemon)).collect(Collectors.toList());
		int fights = 0;

		for (int fighterPosition = 0; fighterPosition < sortedParticipants.size(); fighterPosition++) {
			ResultWrapper participant = sortedParticipants.get(fighterPosition);
			int difference = fighterPosition - participant.resultPosition;
			if (difference > 2) {
				throw new RuntimeException("Parece que el equipo Rocket pudo haber manipulado el resultado");
			}
			if (difference > 0) {
				fights += difference;
			}
		}

		return fights;
	}

	@Data
	private class ResultWrapper {
		private final int resultPosition;
		private final PokemonBase pokemon;
	}
}
