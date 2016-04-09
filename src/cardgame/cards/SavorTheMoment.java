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
        public boolean setTarget() {return false; }

        @Override
        public boolean hasTarget() {
            return false;
        }

        @Override
        public void resolve() {
            CardGame.instance.get_triggers().register(16, new TriggerAction() {
                @Override
                public void execute() {
                    System.out.println("taking the extra turn");
                    Player[] Players = new Player[2];
                    Players[0] = CardGame.get_current_player();
                    Players[1] = CardGame.get_current_adversary();
                    DefaultTurnManager tm = new DefaultTurnManager(Players);
                    CardGame.set_turn_manager(tm);
                    // Trigger nella end phase per fare remove_turn_manager();
                    owner.set_phase(Phases.NULL, new SkipPhase(Phases.UNTAP));

                }
            });
            System.out.println(owner.get_name() + " will take an extra turn but he won't untap his creatures");
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

