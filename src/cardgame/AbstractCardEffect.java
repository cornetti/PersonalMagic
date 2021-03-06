
package cardgame;



// utility class implementing code common to all effects linked with cards:
// remove card from hand and place the effect on the stack on play
public abstract class AbstractCardEffect extends AbstractEffect {
    
    protected Player owner;
    protected Card card;
    protected Player opponent;
    protected AbstractCardEffect effectTarget;
    protected Creature creatureTarget;
    protected Player playerTarget;
    protected boolean played = false;
    protected AbstractCardEffect target;
    
    protected AbstractCardEffect(Player p, Card c) {
        this.owner=p;
        this.card=c;
        this.opponent = CardGame.instance.get_current_adversary();
    }
    
    public boolean play() {
        owner.get_hand().remove(card);
        played = true;
        setTarget();
        return super.play();
    }

    public boolean getPlayed() {
        return played;
    }

    public AbstractCardEffect getTarget() {
        return target;
    }

    public abstract void setTarget();

    public String toString() { return card.toString(); }

    public AbstractCardEffect getEffectTarget(){
        return effectTarget;
    }

    public abstract boolean hasTarget();
}
