
package kiwiapollo.wanteditems.luckyegg;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.Species;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kiwiapollo.wanteditems.common.SimpleFactory;

public class RandomPokemonFactory implements SimpleFactory<Pokemon> {
    private static final int LEVEL = 10;
    private static final List<String> FORBIDDEN_SPECIES = List.of();
    private final List<String> required;
    private final List<String> forbidden;

    public RandomPokemonFactory() {
        this(List.of(), List.of());
    }

    public RandomPokemonFactory(List<String> required, List<String> forbidden) {
        this.required = required;
        this.forbidden = forbidden;
    }

    public Pokemon create() {
        List<Species> random = new ArrayList<>(PokemonSpecies.INSTANCE.getImplemented().stream().filter((species) -> {
            return !FORBIDDEN_SPECIES.contains(species.getResourceIdentifier().toString());
        }).filter((species) -> {
            return this.required.isEmpty() || this.required.stream().anyMatch((label) -> {
                return species.getLabels().contains(label);
            });
        }).filter((species) -> {
            return this.forbidden.isEmpty() || this.forbidden.stream().noneMatch((label) -> species.getLabels().contains(label));
        }).toList());
        Collections.shuffle(random);
        return random.getFirst().create(10);
    }
}
