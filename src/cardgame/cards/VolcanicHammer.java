package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class VolcanicHammer implements Card {

    private class VolcanicHammerEffect extends AbstractCardEffect {
        public VolcanicHammerEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new VolcanicHammerEffect(owner,this);
    }

    @Override
    public String name() {
        return "Volcanic hammer";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "Volcanic Hammer deals 3 damage to anyone creature or player";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
