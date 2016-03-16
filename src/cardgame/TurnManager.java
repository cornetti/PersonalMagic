package cardgame;


public interface TurnManager {
    Player get_current_player();
    
    Player get_current_adversary();
    
    Player next_player();
}