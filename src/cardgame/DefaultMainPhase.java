package cardgame;

import java.util.ArrayList;
import java.util.Scanner;


public class DefaultMainPhase implements Phase {

    
    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        int response_player_idx = (CardGame.instance.get_player(0) == current_player)?1:0;
        
        System.out.println(current_player.get_name() + ": main phase");
        
        CardGame.instance.get_triggers().trigger(Phases.MAIN_FILTER);
        
        
        // alternate in placing effect until bith players pass
        int number_passes=0;
        
        if (!play_available_effect(current_player, true))
            ++number_passes;
        
        while (number_passes<2) {
            if (play_available_effect(CardGame.instance.get_player(response_player_idx),false))
                number_passes=0;
            else ++number_passes;
            
            response_player_idx = (response_player_idx+1)%2;
        }
        
        CardGame.instance.get_stack().resolve();
    }
    
    
    // looks for all playable effects from cards in hand and creatures in play
    // and asks player for which one to play
    // includes creatures and sorceries only if is_main is true
    private boolean play_available_effect(Player active_player, boolean is_main) {
        //collect and display available effects...
        ArrayList<Effect> available_effects = new ArrayList<>();
        Scanner reader = CardGame.instance.get_scanner();

        //...cards first
        System.out.println(active_player.get_name() + " select card/effect to play, 0 to pass");
        int i=0;
        for( Card c:active_player.get_hand() ) {
            if ( is_main || c.isInstant() ) {
                available_effects.add( c.get_effect(active_player) );
                System.out.println(Integer.toString(i+1)+") " + c );
                ++i;
            }
        }
        
        //...creature effects last
        for ( Creature c:active_player.get_creatures()) {
            for (Effect e:c.avaliable_effects()) {
                available_effects.add(e);
                System.out.println(Integer.toString(i+1)+") " + c.name() + 
                    " ["+ e + "]" );
                ++i;
            }
        }
        
        //get user choice and play it
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=available_effects.size()) return false;

        available_effects.get(idx).play();
        return true;
    }
    
    
}
