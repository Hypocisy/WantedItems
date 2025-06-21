
package kiwiapollo.wanteditems.luckyegg;

import com.cobblemon.mod.common.pokemon.Pokemon;
import java.util.List;
import kiwiapollo.wanteditems.common.SimpleFactory;

public class RandomShinyPokemonFactory implements SimpleFactory<Pokemon> {
    private final SimpleFactory<Pokemon> factory;

    public RandomShinyPokemonFactory(List<String> required, List<String> forbidden) {
        this.factory = new RandomPokemonFactory(required, forbidden);
    }

    public RandomShinyPokemonFactory() {
        this(List.of(), List.of());
    }

    public Pokemon create() {
        Pokemon pokemon = this.factory.create();
        pokemon.setShiny(true);
        return pokemon;
    }
}
