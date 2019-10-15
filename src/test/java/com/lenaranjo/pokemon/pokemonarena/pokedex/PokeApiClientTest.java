package com.lenaranjo.pokemon.pokemonarena.pokedex;

import com.lenaranjo.pokemon.pokemonarena.model.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokeApiClientTest {
    private static final String POKEMON_NAME = "bulbasaur";
    private static final int POKEMON_ID = 1;
    private PokeApiClient pokeApiClient;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder invocationBuilder;

    @Before
    public void setUp() {
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.request(anyString())).thenReturn(invocationBuilder);
        Pokemon pokemon = new Pokemon();
        pokemon.setId(POKEMON_ID);
        pokemon.setName(POKEMON_NAME);
        when(invocationBuilder.get(Pokemon.class)).thenReturn(pokemon);

        pokeApiClient = new PokeApiClient(webTarget);
    }

    @Test
    public void getPokemon() {
        Pokemon pokemon = pokeApiClient.getPokemon(POKEMON_NAME);
        assertNotNull(pokemon);
        assertEquals(POKEMON_ID, pokemon.getId());
        assertEquals(POKEMON_NAME, pokemon.getName());

        // Test Cache
        pokemon = pokeApiClient.getPokemon(POKEMON_NAME);
        assertNotNull(pokemon);
        assertEquals(POKEMON_ID, pokemon.getId());
        assertEquals(POKEMON_NAME, pokemon.getName());
        verify(webTarget, atMostOnce()).request(anyString());
    }
}