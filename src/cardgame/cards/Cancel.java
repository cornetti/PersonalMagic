package cardgame.cards;

import cardgame.*;

import java.util.Scanner;

/**
 * Created by mryolo on 16/03/16.
 */
public class Cancel implements Card {

    private class CancelEffect extends AbstractCardEffect{
        Effect target;

        public CancelEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public boolean play(){
            setTarget();
            return super.play();
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
            target = CardGame.instance.get_stack().get(index);

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
                public void setTarget() {
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
}
