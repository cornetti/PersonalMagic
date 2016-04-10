package cardgame.cards;

import cardgame.*;

import java.util.ArrayList;

/**
 * Created by Kotono on 16/03/2016.
 */
public class WorldAtWar implements Card {

    private class WorldAtWarEffect extends AbstractCardEffect {
        public WorldAtWarEffect(Player p, Card c) {
            super(p, c);
        }
        private ArrayList<Creature> toUntap = new ArrayList<Creature>();
        private int parte = 0;
        private PhaseManager pm;

        @Override
        public void setTarget() {
        }

        @Override
        public boolean hasTarget() {
            return false;
        }

        @Override
        public void resolve() {
            //not working

            CardGame.instance.get_triggers().register(4, new TriggerAction() {

                @Override
                public void execute() {
                    if(parte == 0) {
                        for (Attack atk : AttackList.attacks)
                            toUntap.add(atk.getAttacker());
                        pm = owner.get_phase_manager();
                        pm.prevPhase();
                        parte = 1;
                        CardGame.instance.get_triggers().register(8, this);
                    }else if(parte == 1){
                        owner.set_phase_manager(pm);
                        parte = 2;
                        CardGame.instance.get_triggers().register(4, this);
                    }else if(parte == 2){
                        for (Creature c : toUntap)
                            c.untap();
                        parte = 3;
                        CardGame.instance.get_triggers().register(8 ,this);
                    }else{
                        owner.remove_phase_manager(pm);
                    }
                }

            });
        }
    }


    @Override
    public Effect get_effect(Player owner) {
        return new WorldAtWarEffect(owner,this);
    }

    @Override
    public String name() {
        return "World at War";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "After the first postcombat main phase this turn, there's an additional combat phase followed by an additional main phase. At the beginning of that combat, untap all creatures that attacked this turn";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}

