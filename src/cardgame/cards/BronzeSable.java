package cardgame.cards;

import cardgame.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotono on 16/03/2016.
 */
public class BronzeSable implements Card {

    private class BronzeSableEffect extends AbstractCreatureCardEffect{
        public BronzeSableEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void setTarget() {

        }

        @Override
        public boolean hasTarget() {
            return false;
        }

        protected Creature create_creature() { return new BronzeSableCreature(owner); }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new BronzeSableEffect(owner,this);
    }

    private class BronzeSableCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();


        BronzeSableCreature(Player owner) { /*Costruttore*/
            super(owner);

            power = 2;
            toughness = 1;

            all_effects.add(new Effect() {
                public boolean play() {                             /*errore, sta carta non ha effetto, così ha l'effetto di tap:non fa niente*/
                    CardGame.instance.get_stack().add(this);
                    return tap();
                }

                public void resolve() {
                }

                public String toString() {
                    return "Bronze Sable";
                }
            });
        }

        public String name() { return "Bronze Sable"; }

        public int get_power(){return this.power;}
        public int get_toughness(){return this.toughness;}



        public List<Effect> effects() { return all_effects; }
        public List<Effect> avaliable_effects() { return (is_tapped)?tap_effects:all_effects; }
    }


    @Override
    public String name() {
        return "Bronze Sable";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String rule_text() {
        return "Attack: 2 Toughness: 1 ";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
