package cardgame.cards;

import cardgame.*;

/**
 * Created by Kotono on 16/03/2016.
 */
public class NorwoodRanger implements Card {

    private class NorwoodRangerEffect extends AbstractCreatureCardEffect {
        public NorwoodRangerEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        protected Creature create_creature() {
            return null;
        }

        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new NorwoodRangerEffect(owner,this);
    }

    @Override
    public String name() {
        return "Norwood Ranger";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String rule_text() {
        return "The song of the forest is in perfect harmony. If a single note is out of place, the elves will find its source";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
