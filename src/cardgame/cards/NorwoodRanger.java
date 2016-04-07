package cardgame.cards;

import cardgame.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotono on 16/03/2016.
 */
public class NorwoodRanger implements Card {

    private class NorwoodRangerEffect extends AbstractCreatureCardEffect {
        public NorwoodRangerEffect(Player p, Card c){
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
        protected Creature create_creature() {
            return new NorwoodRangerCreature(owner);
        }

    }

    @Override
    public Effect get_effect(Player owner) {
        return new NorwoodRangerEffect(owner,this);
    }

       private class NorwoodRangerCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();

        NorwoodRangerCreature(Player owner) { /*Costruttore*/
            super(owner);
            power = 1;
            toughness = 2;
            all_effects.add( new Effect() {
                                 public boolean play() {
                                     CardGame.instance.get_stack().add(this);
                                     return tap();
                                 }

                                 public void resolve() {}

                                 public String toString()
                                 { return "Norwood Ranger"; }
                             }
            );
        }

        public String name() { return "Norwood Ranger"; }

        public void attack() {}
        public void defend(Creature c) {}
        public int get_power() { return this.power; }
        public int get_toughness() { return this.toughness; }

        public List<Effect> effects() { return all_effects; }
        public List<Effect> avaliable_effects() {
            boolean is_tapped = false;
            return (is_tapped) ? tap_effects:all_effects; }
    }

    @Override
    public String name() {
        return "Norwood Ranger";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String rule_text() {
        return "The song of the forest is in perfect harmony. If a single note is out of place, the elves will find its source";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
