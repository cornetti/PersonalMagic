package cardgame;

import java.lang.reflect.Array;
import java.util.ArrayList;

// utility class to implementing behavior common to all effects,
// namely placing it in the stack when played
public abstract class AbstractEffect implements Effect {
    protected ArrayList<? extends Target> targets;

    public boolean play() {
        CardGame.instance.get_stack().add(this);
        return true;
    }

    public ArrayList<? extends Target> getTarget() {
        return targets;
    }

    public void setTarget (ArrayList<? extends Target> targets) {
        this.targets = targets;
    }
}
