package cardgame.cards;

import cardgame.*;

/**
 * Created by Kotono on 16/03/2016.
 */
public class Darkness implements Card {

    private class DarknessEffect extends AbstractCardEffect {
        public DarknessEffect(Player p, Card c){
            super(p,c);
        }

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
            CardGame.instance.get_triggers().register(4, new TriggerAction() {
                @Override
                public void execute() {
                    AttackList.reset();
                }
            });
        }
    }

    @Override
    public Effect get_effect(Player owner) {
        return new DarknessEffect(owner,this);
    }

    @Override
    public String name() {
        return "Darkness";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Prevent all combat damage that would be dealt this turn";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}

