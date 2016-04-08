package cardgame;


public class DefaultDrawPhase implements Phase {
    

    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        
        System.out.println("\n<-----O--- " + current_player.get_name() + ": draw phase ---O----->");
        
        CardGame.instance.get_triggers().trigger(Phases.DRAW_FILTER);
        current_player.draw();
        
        while(current_player.get_hand().size() > current_player.get_max_hand_size())
            current_player.select_discard();
    }
}
