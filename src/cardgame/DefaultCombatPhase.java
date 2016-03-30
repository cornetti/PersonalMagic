
package cardgame;


import java.util.Scanner;

public class DefaultCombatPhase implements Phase {

    Player current_player = CardGame.instance.get_current_player();
    Player current_adversary = CardGame.instance.get_current_adversary();

    public void execute() {
        int boh = 0;
        CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);

        System.out.println(current_player.get_name() + ": combat phase");

        declareAttackers();
        instants
        declareBlockers();
        instants
        calculateDamages();

        // TODO combat

        /*
        * Dichiara attaccanti per il giocatore attivo:
        *   attaccante decide chi attacca
        * Dichiara difensori per l'altro giocatore:
        *   difensore distribuisce il danno tra le sue creature
        * Calcolo dei danni
        * End combat
        * */
    }

        /**/

    //dal campo dichiaro quali mostri attaccare
    public void declareAttackers () {
        boolean end = false;
        int index;
        Scanner in = new Scanner(System.in);
        for(Creature c: CardGame.instance.get_current_player().get_creatures()){
            System.out.println("vuoi attaccare con " + c.name() + "?\n Y/n" );
            if (in.next()=="y")
                c.attack();//attack prende come parametro il target e un intero!!! (vedi classe abstractCreature)
        }
    }

    public void declareBlockers () {
        boolean end = false;
        int index;
        Scanner in = new Scanner(System.in);
        for(Creature c: CardGame.instance.get_current_adversary().get_creatures()){
            System.out.println("vuoi difendere con " + c.name() + "?\n Y/n" );
            if (in.next()=="y") {
                for(Creature c: CardGame.instance.get_current_adversary().get_creatures()){
                    System.out.println("Vuoi difendere da questo? " + c.name() + "?\n Y/n" );
                    if (in.next()=="y") //dadefinire
                }
                c.defend();
        }
    }

    public int calculateDamages () {
        return 0;
    }


    
}

