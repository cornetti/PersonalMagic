package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 * Created by Kotono on 16/03/2016.
 */
public class Darkness implements Card {

    private class DarknessEffect extends AbstractCardEffect {
        public DarknessEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public boolean play() {
            return super.play();
        }

        @Override
        public boolean hasTarget() {
            return false;
        }

        @Override
        public void resolve() {
            //TODO bisogna far la fase finale della combat
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new DarknessEffect(owner,this);
    }

    @Override
    public String name() {
        return "Darkness";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Prevent all combat damage that would be dealt this turn";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
