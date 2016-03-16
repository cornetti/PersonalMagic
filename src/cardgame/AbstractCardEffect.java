
package cardgame;

// utility class implementing code common to all effects linked with cards:
// remove card from hand and place the effect on the stack on play
public abstract class AbstractCardEffect extends AbstractEffect {
    
    protected Player owner;
    protected Card card;
    
    protected AbstractCardEffect(Player p, Card c) { owner=p; card=c; }
    
    public boolean play() { 
        owner.get_hand().remove(card);
        return super.play();
    }
    
    public String toString() { return card.toString(); }
    
}
