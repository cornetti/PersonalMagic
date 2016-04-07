
package cardgame;


import java.util.ArrayList;
import java.util.Scanner;

public class DefaultCombatPhase implements Phase {


    public ArrayList<Attack> getAttacks(){ return AttackList.attacks;}

    public void execute() {

        Player current_player = CardGame.instance.get_current_player();
        //Player current_adversary = CardGame.instance.get_current_adversary();

        System.out.println(current_player.get_name() + ": combat phase");

        declareAttackers();
        declareBlockers();

        CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);
        calculateDamages();
        // L'ho TODATO

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
        int index = 0;
        Scanner in = new Scanner(System.in);
        for(Creature c: CardGame.instance.get_current_player().get_creatures()){
            if(c.isTapped() == false) {
                System.out.println("vuoi attaccare con " + c.name() + "?\n y/n");
                if (in.next().equals("y"))
                    c.attack();
            }
        }
    }

    public void declareBlockers () {
        boolean end = false;
        int index;
        Scanner in = new Scanner(System.in);

        // Invece di scorrere le creature in gioco (che alcune magari non sono neanche attaccanti)
            // scorriamo le istanze di attack presenti nella combat.
            for(Creature c: CardGame.instance.get_current_adversary().get_creatures()) {
                if(c.isTapped() == false) {
                    System.out.println("vuoi difendere con " + c.name() + "?\n y / n");
                    if (in.next().equals("y")) {
                        for (Attack atk : AttackList.attacks) {
                            System.out.println("Vuoi difendere da: " + atk.getAttacker().name() + "?\n y / n");
                            if (in.next().equals("y"))
                                c.defend(atk.getAttacker());
                        }
                    }
                }
            }
    }

    public void calculateDamages () {
        // dato che abbiamo registrato tutte le dichiarazioni di attacco e difesa
        //  negli attack allora basta richiamare il resolve di ogni attack.
        for(Attack atk: AttackList.attacks)
            atk.resolve();
        AttackList.reset();
    }


    
}

