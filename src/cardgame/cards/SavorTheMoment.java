package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class SavorTheMoment implements Card {

    private class CancelEffect extends AbstractCardEffect {
        public CancelEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public String rule_text() {
        return null;
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
