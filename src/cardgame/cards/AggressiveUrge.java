package cardgame.cards;

import cardgame.*;

import java.util.Scanner;

/**
 * Created by Kotono on 16/03/2016.
 */
public class AggressiveUrge implements Card {

    private class AggressiveUrgeEffect extends AbstractCardEffect {
        Creature target;

        /*costruttore*/
        public AggressiveUrgeEffect(Player p, Card c){
            super(p,c);
        }


        public boolean play(){
            System.out.println("possibili target in campo:");
            int index = 0;
            for (Creature c: CardGame.instance.get_current_player().get_creatures()){
                System.out.println(index +".  "+ c.name() + ": " + c.get_power() + "/" + c.get_toughness());
                ++index;
            }

            System.out.println("inserire l'indice del target");
            Scanner scanner = new Scanner(System.in);
            index = scanner.nextInt();

            target = (CardGame.instance.get_current_player().get_creatures().get(index));
            return super.play();
        }

        @Override
        public void resolve() {
            //TODO serve trigger
            target.weaken(-1);
            target.inflict_damage(-1);
        }
    }


    @Override
    public Effect get_effect(Player owner) {
        return new AggressiveUrgeEffect(owner,this);
    }

    @Override
    public String name() {
        return "Aggressive Urge";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Target creature gets +1/+1 until end of turn";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
