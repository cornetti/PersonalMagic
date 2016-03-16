package cardgame;


public class DefaultEndPhase implements Phase {
    
    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        
        System.out.println(current_player.get_name() + ": end phase");
        
        CardGame.instance.get_triggers().trigger(Phases.END_FILTER);
        
        for(Creature c:current_player.get_creatures()) {
            System.out.println("...reset damage to " + c.name());
            c.reset_damage();
        }
        
        for(Creature c:CardGame.instance.get_current_adversary().get_creatures()) {
            System.out.println("...reset damage to adversary creature " + c.name());
            c.reset_damage();
        }
    }
    
}
