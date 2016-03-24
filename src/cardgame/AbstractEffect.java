package cardgame;

import java.util.ArrayList;

// utility class to implementing behavior common to all effects,
// namely placing it in the stack when played
public abstract class AbstractEffect implements Effect {
    protected ArrayList<? extends Target> targets;

    public boolean play(ArrayList<? extends Target> targets) {
        this.targets = targets;
        CardGame.instance.get_stack().add(this);
        return true;
    }
    
}
