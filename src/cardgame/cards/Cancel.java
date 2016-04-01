package cardgame.cards;

import cardgame.*;

/**
 * Created by mryolo on 16/03/16.
 */
public class Cancel implements Card {

    private class CancelEffect extends AbstractCardEffect{
        Effect target;


        public CancelEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {
            //TODO accedere allo stack
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new CancelEffect(owner,this);
    }

    @Override
    public String name() {
        return "Cancel";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return name() + " counter target spell";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
