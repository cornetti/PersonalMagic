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
        public void resolve() {/*
            Player players[] = new Player[2];
            //not working
            players[0] = CardGame.instance.get_current_player();
                                players[1] = CardGame.instance.get_current_adversary();
                                final DefaultTurnManager tm = new DefaultTurnManager(players);
                                CardGame.instance.set_turn_manager(tm);
                                CardGame.instance.get_triggers().register(16, new TriggerAction() {
                                        @Override
                                        public void execute() {
                                                CardGame.instance.remove_turn_manager(tm);
                                            }
                                    });
            // Trigger nella end phase per fare remove_turn_manager();
            owner.set_phase(Phases.NULL, new SkipPhase(Phases.UNTAP));
            */
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

