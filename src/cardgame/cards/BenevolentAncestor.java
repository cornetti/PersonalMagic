package cardgame.cards;

import cardgame.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kotono on 16/03/2016.
 */
public class BenevolentAncestor implements Card {

    private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {
        public BenevolentAncestorEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void setTarget() {}

        @Override
        protected Creature create_creature() {
            return new BenevolentAncestorCreature(owner);
        }

        @Override
        public boolean hasTarget() {
            return false;
        }

    }

    @Override
    public Effect get_effect(Player owner) {
        return new BenevolentAncestorEffect(owner,this);
    }

    private class BenevolentAncestorCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();

        BenevolentAncestorCreature(Player owner) { /*Costruttore*/
            super(owner);
            power = 0;
            toughness = 4;
            all_effects.add( new Effect() {
                                private int targetIndex;
                                public boolean play() {
                                    CardGame.instance.get_stack().add(this);
                                    return tap();
                                }

                                public void resolve() {
                                     AttackList.remove(targetIndex);
                                 }
                                public String toString()
                                { return "Benevolent Ancestor"; }

                                public void setTarget(){
                                    int index = 0;
                                    System.out.println("possible attacks to block");
                                    Scanner reader = new Scanner(System.in);
                                    for (Attack a : AttackList.attacks){
                                        System.out.println(index++ + ". from: " + a.getAttacker().name() +" amount: "+ a.getAttacker().get_power());
                                    }
                                    System.out.println("insert index of target attack");
                                    targetIndex = reader.nextInt();
                                }

                             }
            );
        }

        @Override
        public void attack() {
            System.out.println(name() + " cannot attack");
        }

        @Override
        public boolean hasEffect() {
            return true;
        }

        public String name() { return "Benevolent Ancestor"; }


        public int get_power() { return power; }
        public int get_toughness() { return toughness; }

        public List<Effect> effects() { return all_effects; }
        public List<Effect> avaliable_effects() { return (is_tapped)?tap_effects:all_effects; }
        }

    @Override
    public String name() {
        return "Benevolent Ancestor";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String rule_text() {
        return "Prevent the next 1 damage that would be dealt to target creature or player this turn";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}

