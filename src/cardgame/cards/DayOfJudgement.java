package cardgame.cards;

import cardgame.*;

/**
 * Created by Kotono on 16/03/2016.
 */
public class DayOfJudgement implements Card {

    private class DayOfJudgementEffect extends AbstractCardEffect {
        public DayOfJudgementEffect(Player p, Card c){
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
        public void resolve() {
            opponent.get_creatures().clear();
            owner.get_creatures().clear();
            System.out.println("all creature destroyed");
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new DayOfJudgementEffect(owner,this);
    }

    @Override
    public String name() {
        return "Day of Judgement";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "Destroy all creatures";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
