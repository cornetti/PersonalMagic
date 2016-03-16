package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import java.util.List;
import java.util.ArrayList;

public class Reflexologist implements Card{
    
    private class ReflexologistEffect extends AbstractCreatureCardEffect {
        public ReflexologistEffect(Player p, Card c) { super(p,c); }
        
        protected Creature create_creature() { return new ReflexologistCreature(owner); }
    }
    public Effect get_effect(Player p) { return new ReflexologistEffect(p,this); }
    
    
    private class ReflexologistCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        ReflexologistCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    public boolean play() { 
                                        CardGame.instance.get_stack().add(this);
                                        return tap(); 
                                    }
                                    public void resolve() {}
                                    public String toString() 
                                        { return "tap: Reflexology does nothing"; }
                                }
                ); 
        }
        
        public String name() { return "Reflexologist"; }
        
        public void attack() {}
        public void defend(Creature c) {}
        public int get_power() { return 0; }
        public int get_toughness() { return 1; }

        public List<Effect> effects() { return all_effects; }
        public List<Effect> avaliable_effects() { return (is_tapped)?tap_effects:all_effects; }
    }
    
    
    public String name() { return "Reflexologist"; }
    public String type() { return "Creature"; }
    public String rule_text() { return "Put in play a creature Reflexologist(0/1) with tap: Reflexology does nothing"; }
    public String toString() { return name() + "[" + rule_text() +"]";}
    public boolean isInstant() { return false; }

}
