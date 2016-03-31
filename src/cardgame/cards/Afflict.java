package cardgame.cards;

import cardgame.*;

import java.util.Scanner;

/**
 * Created by Kotono on 16/03/2016.
 */
public class Afflict implements Card {

    private class AfflictEffect extends AbstractCardEffect {
        Creature target;

        public AfflictEffect(Player p, Card c){
            super(p,c);
        }


        @Override
        public boolean play(){
            System.out.println("possibili target in campo:");
            int index = 0;
            for (Creature c: CardGame.instance.get_current_adversary().get_creatures()){
                System.out.println(index +".  "+ c.name() + ": " + c.get_power() + "/" + c.get_toughness());
                ++index;
            }

            System.out.println("inserire l'indice del target");
            Scanner scanner = new Scanner(System.in);
            index = scanner.nextInt();

            target = (CardGame.instance.get_current_adversary().get_creatures().get(index));
            return false;
        }

        @Override
        public void resolve() {
            target.inflict_damage(1);
            //TODO serve trigger
            target.weaken(1);
            }
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new AfflictEffect(owner,this);
    }

    @Override
    public String name() {
        return "Afflict";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Target creature gets -1/-1 until end of turn";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
