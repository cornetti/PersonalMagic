package cardgame.cards;

import cardgame.*;

/**
 * Created by Kotono on 16/03/2016.
 */
public class SavorTheMoment implements Card {

    private class SavorTheMomentEffect extends AbstractCardEffect {
        public SavorTheMomentEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public boolean play() {
            return super.play();
        }

        @Override
        public void setTarget() {

        }

        @Override
        public boolean hasTarget() {
            return false;
        }

        @Override
        public void resolve() {
            //TODO vedere se va bene
            owner.set_phase(Phases.DRAW, new DefaultDrawPhase());
            owner.set_phase(Phases.COMBAT, new DefaultCombatPhase());
            owner.set_phase(Phases.MAIN, new DefaultMainPhase());
            owner.set_phase(Phases.END, new DefaultEndPhase());
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

    public String toString() { return name() + "[" + rule_text() +"]";}
}

