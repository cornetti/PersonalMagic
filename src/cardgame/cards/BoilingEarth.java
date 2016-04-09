package cardgame.cards;

import cardgame.*;

import java.util.Iterator;
import java.util.List;

//tested
public class BoilingEarth implements Card {

    private class BoilingEarthEffect extends AbstractCardEffect {
        public BoilingEarthEffect(Player p, Card c){
            super(p,c);
        }

        public List<Creature> target = opponent.get_creatures();

        @Override
        public boolean setTarget() {return false;}

        @Override
        public boolean hasTarget() {
            return false;
        }

        @Override
        public void resolve() {
            /**
             * metodo orribile per indebolire le creature e
             * che le elimina personalmente se rimangono con toughness = 0
             **/
            Iterator it = target.iterator();
            while (it.hasNext()) {
                Creature c = (Creature)it.next();
                if (c.get_toughness() > 1) {
                    System.out.println(c.name() + "taken 1 damage from boiling earth");
                    c.inflict_damage(1);
                }
                else {
                    System.out.println(c.name() + " has been destroyed by boiling earth");
                    it.remove();
                }
            }
            //System.out.println("inflicted 1 damage to all creatures");
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new BoilingEarthEffect(owner,this);
    }

    @Override
    public String name() {
        return "Boiling Earth";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "Boiling Earth deals 1 damage to each creature";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
