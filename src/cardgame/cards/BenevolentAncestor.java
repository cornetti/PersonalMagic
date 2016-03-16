package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class BenevolentAncestor implements Card {

    private class BenevolentAncestorEffect extends AbstractCardEffect {
        public BenevolentAncestorEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new BenevolentAncestorEffect(owner,this);
    }

    @Override
    public String name() {
        return "Benevolent Ancestor";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String rule_text() {
        return "Prevent the next 1 damage that would be dealt to target creature or player this turn";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
