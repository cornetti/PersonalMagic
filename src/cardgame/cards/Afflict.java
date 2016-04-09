package cardgame.cards;

import cardgame.*;

import java.util.Scanner;

/**
 * Created by Kotono on 16/03/2016.
 */
public class Afflict implements Card {

    private class AfflictEffect extends AbstractCardEffect {
        Creature target;

        /*costruttore*/
        public AfflictEffect(Player p, Card c){
            super(p,c);
        }



        @Override
        public boolean setTarget(){
            System.out.println("possibili target in campo:");
            int index = 0;
            for (Creature c: opponent.get_creatures()){
                System.out.println(++index +".  "+ c.name() + ": " + c.get_power() + "/" + c.get_toughness());
            }

            System.out.println("insert index of target or 0 for abort");
            Scanner scanner = new Scanner(System.in);
            index = scanner.nextInt()-1;
            if (index>0){
                target = (opponent.get_creatures().get(index));
                return true;
            }
            System.out.println("aborted");
            return false;

        }

        @Override
        public boolean hasTarget() {
            return true;
        }

        @Override
        public void resolve() {
            target.weaken(1,1);

            CardGame.instance.get_triggers().register(16, new TriggerAction() {
                @Override
                public void execute() {
                    target.weaken(-1,-1);
                }
            });


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

    public String toString() { return name() + "[" + rule_text() +"]";}
}
