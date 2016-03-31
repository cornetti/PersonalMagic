package cardgame.cards;

import cardgame.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotono on 16/03/2016.
 */
public class BronzeSable implements Card {

    private class BronzeSableEffect extends AbstractCardEffect{
        public BronzeSableEffect(Player p, Card c){
            super(p,c);
        }

        protected Creature create_creature() { return new BronzeSableCreature(owner); }

        @Override
        public void resolve() {

        }
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

            power = 0;
            toughness = 1;

            all_effects.add( new Effect() {
                                 public boolean play() {
                                     CardGame.instance.get_stack().add(this);
                                     return tap();
                                 }

                                 public void resolve() {}

                                 public String toString()
                                 { return "Bronze Sable"; } /*commento dummy*/
                             }
            );
        }

        public String name() { return "Bronze Sable"; }

        public void attack() {}
        public void defend(Creature c) {}

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
        return "The Champion stood alone between the horde of the Returned and the shrine to Karametra, cutting down scores among hundreds. She would have been overcome if not for the aid of the temple guardians whom Karametra awakened.\n" +
                "—The Theriad";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
