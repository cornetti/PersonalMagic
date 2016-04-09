package cardgame.cards;

import cardgame.*;

import java.util.Scanner;


//works
public class Cancel implements Card {

    private class CancelEffect extends AbstractCardEffect{
        Effect target;

        public CancelEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public boolean setTarget() {
            int index = 0;
            System.out.println("Possibili effetti target nello stack");
            for (Effect e : CardGame.instance.get_stack()){
                index++;
                System.out.println(index+ ". " + e.toString());
            }
            System.out.println("inserire l'indice del target");
            Scanner in = new Scanner(System.in);
            index = in.nextInt()-1;
            if (index>0){
                target = CardGame.instance.get_stack().get(index);
                return true;
            }
            System.out.println("aborted");
            return false;
        }

        @Override
        public boolean hasTarget() {
            return true;
        }

        @Override
        public void resolve() {
            target = new AbstractCardEffect(owner,card) {
                @Override
                public boolean play() {
                    return super.play();
                }

                @Override
                public boolean setTarget() {
                    return false;
                }

                @Override
                public boolean hasTarget() {
                    return false;
                }

                @Override
                public void resolve() {

                }

            };
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
