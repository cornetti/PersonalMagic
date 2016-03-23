package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class AggressiveUrge implements Card {

    private class AggressiveUrgeEffect extends AbstractCardEffect {
        public AggressiveUrgeEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {
            //lel
        }
    }


    @Override
    public Effect get_effect(Player owner) {
        return new AggressiveUrgeEffect(owner,this);
    }

    @Override
    public String name() {
        return "Aggressive Urge";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Target creature gets +1/+1 until end of turn";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
