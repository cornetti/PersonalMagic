package cardgame.cards;

import cardgame.*;


public class FalsePeace implements Card {

    private class FalsePeaceEffect extends AbstractCardEffect {
        public FalsePeaceEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void setTarget() {

        }

        @Override
        public boolean hasTarget() {
            return false;
        }

        @Override
        public void resolve()
        { //TODO vedere se va bene
          opponent.set_phase(Phases.COMBAT,new SkipPhase(Phases.COMBAT));
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

    public String toString() { return name() + "[" + rule_text() +"]";}
}
