package cardgame;

public class DefaultTurnManager implements TurnManager {

    private final Player[] Players;
    int current_player_idx=1;
    
    public DefaultTurnManager(Player[] p) { Players=p; }
    
    public Player get_current_player() { return Players[current_player_idx]; }
    
    public Player get_current_adversary() { return Players[(current_player_idx+1)%2]; }
    
    public Player next_player() { 
        current_player_idx = (current_player_idx+1)%2;
        return get_current_player();
    }
}
