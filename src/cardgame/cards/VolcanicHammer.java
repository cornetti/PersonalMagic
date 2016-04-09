package cardgame.cards;

import cardgame.*;

import java.util.Scanner;

//works
public class VolcanicHammer implements Card {

    private class VolcanicHammerEffect extends AbstractCardEffect {
        Creature target = null;
        Player target2 = null;


        /*costruttore*/
        public VolcanicHammerEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public boolean setTarget() {
            target = null;
            target2 = null;
            System.out.println("possibili target in campo:");
            int index = 0;
            for (Creature c: opponent.get_creatures()){
                System.out.println(index +".  "+ c.name() + ": " + c.get_power() + "/" + c.get_toughness());
                ++index;
            }
            System.out.println("inserire l'indice del target (-1 per colpire il giocatore) o 0 per annullare");
            Scanner scanner = new Scanner(System.in);
            index = scanner.nextInt()-1;
            if (index >= 0)
                target = (opponent.get_creatures().get(index));
            else if (index == -2)
                target2 = opponent;
            else {
                System.out.println("aborted");
                return false;
            }
            return true;
        }

        @Override
        public boolean hasTarget() {
            return true;
        }

        @Override
        public void resolve() {
            if(target != null)
                target.inflict_damage(3);
            else
                target2.inflict_damage(3);

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new VolcanicHammerEffect(owner,this);
    }

    @Override
    public String name() {
        return "Volcanic hammer";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String rule_text() {
        return "Volcanic Hammer deals 3 damages to target creature or player";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
