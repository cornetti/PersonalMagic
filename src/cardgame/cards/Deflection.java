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
        public boolean setTarget(){
            int index = 0;
            System.out.println("Possibili effetti target nello stack");
            for (Effect e : CardGame.instance.get_stack()){
                index++;
                if (e instanceof AbstractCardEffect && ((AbstractCardEffect) e).hasTarget())
                    System.out.println(index+ ". " + e.toString());
            }
            System.out.println("inserire l'indice del target");
            Scanner in = new Scanner(System.in);
            index = in.nextInt()-1;
            if (index>0){
                target = CardGame.instance.get_stack().get(index);
                return true;
            }
            else{
                System.out.println("aborted");
                return false;
            }
        }


        @Override
        public boolean hasTarget() {
            return true;
        }


        @Override
        public void resolve() {
            target.setTarget();
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
