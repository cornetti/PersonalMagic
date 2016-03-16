package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class SavorTheMoment implements Card {

    private class SavorTheMomentEffect extends AbstractCardEffect {
        public SavorTheMomentEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new SavorTheMomentEffect(owner,this);
    }

    @Override
    public String name() {
        return "Savor the Moment";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "Take an extra turn after this one. Skip the untap step of that turn";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
