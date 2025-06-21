
package kiwiapollo.wanteditems.luckyegg;

import java.util.List;

public class SilverLuckyEgg extends LuckyEgg {
    public SilverLuckyEgg() {
        super(new RandomPokemonFactory(List.of(), List.of("legendary", "mythical", "paradox", "ultra_beast")));
    }
}
