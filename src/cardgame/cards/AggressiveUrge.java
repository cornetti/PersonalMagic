package cardgame.cards;

import cardgame.*;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by Kotono on 16/03/2016.
 */
public class AggressiveUrge implements Card {

    private class AggressiveUrgeEffect extends AbstractCardEffect {
        public AggressiveUrgeEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        public boolean play(){
            //TODO il giocatore deve poter scegliere anche i target
            return false;
        }


        @Override
        public void resolve() {
            Target t = targets.get(0);
            if (t instanceof Creature) {
                ((Creature) t).inflict_damage(-1);
                ((Creature) t).weaken(-1);
            }
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
