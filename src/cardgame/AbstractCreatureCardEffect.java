package cardgame;

// utility class implementing common behavior for effects linked with 
// creature cards
public abstract class AbstractCreatureCardEffect extends AbstractCardEffect {
    protected AbstractCreatureCardEffect( Player p, Card c) { super(p,c); }
    
    // deferred method that creates the creature upon resolution
    protected abstract Creature create_creature();
    
    public void resolve() {
        owner.get_creatures().add(create_creature());
    }
    
}
