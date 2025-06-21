
package kiwiapollo.wanteditems.luckyegg;

import java.util.List;

public class ShinySilverLuckyEgg extends LuckyEgg {
    public ShinySilverLuckyEgg() {
        super(new RandomShinyPokemonFactory(List.of(), List.of("legendary", "mythical", "paradox", "ultra_beast")));
    }
}
