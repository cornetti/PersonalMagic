package cardgame;

import java.util.ArrayList;

public interface Effect extends Target{
    
    // pays for effect and places it in the stack
    boolean play(ArrayList<? extends Target> targets);
    
    // resolves the effect
    void resolve();

}
