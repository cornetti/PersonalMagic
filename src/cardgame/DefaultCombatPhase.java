
package cardgame;


import java.util.ArrayList;

public class DefaultCombatPhase implements Phase {


    public void execute() {
        int boh = 0;

        Player current_player = CardGame.instance.get_current_player();
        
        System.out.println(current_player.get_name() + ": combat phase");

        /*for(Creature c: CardGame.instance.get_current_adversary().get_creatures())
            c.inflict_damage(boh);*/

        /*CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);*/

        public void declareAttackers () {

    }
        public void declareBlockers () {}

        public int calculateDamages () {}







        // TODO combat

        /*
        * Dichiara attaccanti per il giocatore attivo
        * Dichiara difensori per l'altro giocatore
        * Calcolo dei danni
        * End combat
        * */


    }
    
}
