package cardgame;



public class DefaultCombatPhase implements Phase {
    
    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        
        System.out.println(current_player.get_name() + ": combat phase");
        
        CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);
        // TODO combat
    }
    
}
