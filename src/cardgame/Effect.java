package cardgame;

public interface Effect {
    
    // pays for effect and places it in the stack
    boolean play();
    
    // resolves the effect
    void resolve();

}
