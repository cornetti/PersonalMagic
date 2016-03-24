package cardgame.cards;

import cardgame.*;

/**
 * Created by Kotono on 16/03/2016.
 */
public class Afflict implements Card {

    private class AfflictEffect extends AbstractCardEffect {
        public AfflictEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {
            Target t = targets.get(0);
            if (t instanceof Creature) {
                ((Creature) t).inflict_damage(-1);
                t.
            }
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new AfflictEffect(owner,this);
    }

    @Override
    public String name() {
        return "Afflict";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Target creature gets -1/-1 until end of turn";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
