package cardgame.cards;

import cardgame.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotono on 16/03/2016.
 */
public class BenevolentAncestor implements Card {

    private class BenevolentAncestorEffect extends AbstractCardEffect {
        public BenevolentAncestorEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void resolve() {

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
            all_effects.add( new Effect() {
                                 public boolean play(ArrayList<? extends Target> targets) {
                                     CardGame.instance.get_stack().add(this);
                                     return tap();
                                 }

                                 public void resolve() {}

                                 public String toString()
                                 { return "Benevolent Ancestor"; } /*commento dummy*/
                             }
            );
        }

        public String name() { return "Benevolent Ancestor"; }

        public void attack() {}
        public void defend(Creature c) {}
        public int get_power() { return 0; }
        public int get_toughness() { return 4; }

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
}

