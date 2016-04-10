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
        TurnManager tm;
        private boolean part = true;

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
            tm = CardGame.instance.get_turn_manager();
            CardGame.instance.get_triggers().register(16, new TriggerAction() {
                @Override
                public void execute() {
                    if (part == true){
                        tm.repeatTurn();
                        CardGame.instance.set_turn_manager(tm);
                        part = false;
                        owner.set_phase(Phases.UNTAP,new SkipPhase(Phases.UNTAP));
                        CardGame.instance.get_triggers().register(16, this);
                    }else{
                        CardGame.instance.remove_turn_manager(tm);
                    }

                }
            });

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

