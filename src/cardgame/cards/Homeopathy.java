package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;


public class Homeopathy implements Card{
    
    private class HomeopathyEffect extends AbstractCardEffect {
        public HomeopathyEffect(Player p, Card c) { super(p,c); }
        public void resolve() {}
    }

    public Effect get_effect(Player owner) { 
        return new HomeopathyEffect(owner, this); 
    }
    
    public String name() { return "Homeopathy"; }
    public String type() { return "Instant"; }
    public String rule_text() { return name() + " does nothing"; }
    public String toString() { return name() + "[" + rule_text() +"]";}
    public boolean isInstant() { return true; }
    
}
