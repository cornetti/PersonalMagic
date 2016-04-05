
package cardgame;


import java.util.Scanner;

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
        owner=p;
        card=c;
        opponent = CardGame.instance.get_current_adversary();
    }
    
    public boolean play() {
        owner.get_hand().remove(card);
        played = true;
        return super.play();
    }

    public boolean getPlayed() {
        return played;
    }

    public AbstractCardEffect getTarget() {
        return target;
    }

    public void setTarget() {
        int index = 0;
        System.out.println("Possibili effetti target nello stack");
        for (Effect e : CardGame.instance.get_stack()){
            if (e instanceof AbstractCardEffect && ((AbstractCardEffect) e).hasTarget())
                System.out.println(index+ ". " + e.toString());
            index++;
        }
        System.out.println("inserire l'indice del target");
        Scanner in = new Scanner(System.in);
        index = in.nextInt();
        target = (AbstractCardEffect) CardGame.instance.get_stack().get(index);

    }

    public String toString() { return card.toString(); }

    public AbstractCardEffect getEffectTarget(){
        return effectTarget;
    }

    public abstract boolean hasTarget();
}
