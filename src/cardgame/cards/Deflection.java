package cardgame.cards;

import cardgame.*;

import java.util.Scanner;


public class Deflection implements Card {

    private class DeflectionEffect extends AbstractCardEffect {

        Effect target;

        public DeflectionEffect(Player p, Card c){
            super(p,c);
        }



        @Override
        public void setTarget(){
            int index = 0;
            System.out.println("Possibili effetti target nello stack");
            for (Effect e : CardGame.instance.get_stack()){
                if (e instanceof AbstractCardEffect && ((AbstractCardEffect) e).hasTarget())
                    System.out.println(index+ ". " + e.toString());
                index++;
            }
            System.out.println("inserire l'indice del target");
            Scanner in = new Scanner(System.in);
            index = in.nextInt();
            target = CardGame.instance.get_stack().get(index);
            target.setTarget(); //  <-- forse va nella resolve
        }


        @Override
        public boolean hasTarget() {
            return true;
        }


        @Override
        public void resolve() {

        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new DeflectionEffect(owner,this);
    }

    @Override
    public String name() {
        return "Deflection";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Change the target of target spell with a single target";
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    public String toString() { return name() + "[" + rule_text() +"]";}
}
