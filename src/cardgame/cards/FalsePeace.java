package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class FalsePeace implements Card {

    private class FalsePeaceEffect extends AbstractCardEffect {
        public FalsePeaceEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new FalsePeaceEffect(owner,this);
    }

    @Override
    public String name() {
        return "False Peace";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "Target player skips his next combat phase";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
