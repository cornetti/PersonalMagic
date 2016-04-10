package cardgame.cards;

import cardgame.*;

import java.util.Scanner;


//works
public class Cancel implements Card {

    private class CancelEffect extends AbstractCardEffect{
        int target;

        public CancelEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public void setTarget() {
            int index = 0;
            System.out.println("Possibili effetti target nello stack");
            for (Effect e : CardGame.instance.get_stack()){
                System.out.println(index+ ". " + e.toString());
                index++;
            }
            System.out.println("inserire l'indice del target");
            Scanner in = new Scanner(System.in);
            index = in.nextInt();
            target = index;

        }

        @Override
        public boolean hasTarget() {
            return true;
        }

        @Override
        public void resolve() {
            if (CardGame.instance.get_stack().remove(target)) System.out.println("removed target effect");
            else System.out.println("failed remove");
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new CancelEffect(owner,this);
    }

    @Override
    public String name() {
        return "Cancel";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return name() + " counter target spell";
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
