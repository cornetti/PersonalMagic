package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class BronzeSable implements Card {

    private class BronzeSableEffect extends AbstractCardEffect{
        public BronzeSableEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new BronzeSableEffect(owner,this);
    }

    @Override
    public String name() {
        return "Bronze Sable";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String rule_text() {
        return "The Champion stood alone between the horde of the Returned and the shrine to Karametra, cutting down scores among hundreds. She would have been overcome if not for the aid of the temple guardians whom Karametra awakened.\n" +
                "—The Theriad";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
