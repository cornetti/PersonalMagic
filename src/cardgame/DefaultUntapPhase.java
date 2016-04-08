package cardgame;


public class DefaultUntapPhase implements Phase {
    
    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        
        System.out.println("\n<-----O--- " + current_player.get_name() + ": untap phase ---O----->");
        
        CardGame.instance.get_triggers().trigger(Phases.UNTAP_FILTER);
        
        if (current_player.get_creatures().isEmpty())
            System.out.println("...no creatures to untap");
        
        for(Creature c:current_player.get_creatures()) {
            System.out.println("...untap " + c.name());
            c.untap();
        }
    }
}