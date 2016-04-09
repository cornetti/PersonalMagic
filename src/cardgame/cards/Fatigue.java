package cardgame.cards;

import cardgame.*;

/**
 * Created by Kotono on 16/03/2016.
 */
public class Fatigue implements Card {

    private class FatigueEffect extends AbstractCardEffect {
        public FatigueEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public boolean setTarget() {
            return false;
        }

        @Override
        public boolean hasTarget() {
            return false;
        }

        @Override
        public void resolve()
        {
            opponent.set_phase(Phases.DRAW,new SkipPhase(Phases.DRAW));
            System.out.println("next turn " + opponent.get_name() + " will not draw");
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new FatigueEffect(owner,this);
    }

    @Override
    public String name() {
        return "Fatigue";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "Target player skips his or her next draw step";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
