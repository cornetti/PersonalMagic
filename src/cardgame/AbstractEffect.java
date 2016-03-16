package cardgame;

// utility class to implementing behavior common to all effects, 
// namely placing it in the stack when played
public abstract class AbstractEffect implements Effect {
   public boolean play() { 
        CardGame.instance.get_stack().add(this);
        return true;
    }
    
}
