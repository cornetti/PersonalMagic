
package cardgame;


import java.util.ArrayList;
import java.util.Scanner;

public class DefaultCombatPhase implements Phase {


    public ArrayList<Attack> getAttacks(){ return AttackList.attacks;}
    Player current_player;
    Player current_adversary;

    public void execute() {
        int i = 0;
        current_player = CardGame.instance.get_current_player();
        current_adversary = CardGame.instance.get_current_adversary();

        System.out.println("\n<-----O--- " + current_player.get_name() + ": combat phase ---O----->");

        declareAttackers();
        CardGame.instance.get_stack().resolve();

        // Se non ci sono attaccanti non si chiede di mettere nessun diffensore
        if(getAttacks().isEmpty() == false) {
            declareBlockers();
            CardGame.instance.get_stack().resolve();
            /**
             * Printing state of attacks before damage calculation.
             * Attacker [x;y]
             *   VS
             * (Player z)
             * Defender 1 [x;y]
             * Defender 2 [x;y]
             *   ...
             * Defender n [x;y]
             **/
            System.out.println(current_player.get_name() + " VS " + current_adversary.get_name());
            for (Attack atk : AttackList.attacks) {
                System.out.println("\n~~~~~~~o~~~ Battle nr." + (++i) + " ~~~o~~~~~~~");
                Creature c = atk.getAttacker();
                System.out.println(c.name() + "(" + c.getOwner().get_name() + ")" + " [" + c.get_power() + ";" + c.get_toughness() + "]");
                System.out.println("\tVS");
                if (atk.getDefenders().size() != 0) {
                    for (Creature dif : atk.getDefenders())
                        System.out.println(dif.name() + "(" + dif.getOwner().get_name() + ")" + " [" + dif.get_power() + ";" + dif.get_toughness() + "]");
                }else {
                    System.out.println(current_adversary.get_name() + " (Life points left: " + current_adversary.get_life() + ")");
                }
            }
            System.out.println("~~~~~~~o~~~~~~~o~~~~~~~o~~~~~~~");

        }

        CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);

        calculateDamages();

        // L'ho TODATO

        /**
        * Dichiara attaccanti per il giocatore attivo:
        *   attaccante decide chi attacca
        * Dichiara difensori per l'altro giocatore:
        *   difensore distribuisce il danno tra le sue creature
        * Calcolo dei danni
        * End combat
        * */
    }


    //dal campo dichiaro quali mostri attaccare
    private void declareAttackers () {
        boolean end = false;
        int index = 0;
        Scanner in = new Scanner(System.in);
        for(Creature c: CardGame.instance.get_current_player().get_creatures()){
            if(c.isTapped() == false) {
                System.out.println("vuoi attaccare con " + c.name() + "(" + c.getOwner().get_name() + ")" + "?\n y/n");
                if (in.next().equals("y"))
                    c.attack();
            }
        }
        if(!getAttacks().isEmpty())
            respond(current_adversary);
    }

    private void declareBlockers () {
        boolean end = false;
        int index;
        boolean assigned = false; // Serve ad evitare che con lo stesso difensore difenda più attacchi.
        Scanner in = new Scanner(System.in);

        // Invece di scorrere le creature in gioco (che alcune magari non sono neanche attaccanti)
            // scorriamo le istanze di attack presenti nella combat.
            for(Creature c: CardGame.instance.get_current_adversary().get_creatures()) {
                if(c.isTapped() == false) {
                    System.out.println("vuoi difendere con " + c.name() + "(" + c.getOwner().get_name() + ")" + "?\n y / n");
                    if (in.next().equals("y")) {
                        assigned = false;
                        for (Attack atk : AttackList.attacks) {
                            if(assigned == false) {
                                System.out.println("Vuoi difendere da: " + atk.getAttacker().name() + "(" + atk.getAttacker().getOwner().get_name() + ")" + "?\n y / n");
                                if (in.next().equals("y")) {
                                    c.defend(atk.getAttacker());
                                    assigned = true;
                                }
                            }
                        }
                    }
                }
            }
        if (!getAttacks().isEmpty())
            respond(current_player);
    }

    private void calculateDamages () {
        int i = 0; // Number of current battle
        /**
         * Dato che abbiamo registrato tutte le dichiarazioni di attacco e difesa
         * negli Attack allora basta richiamare il resolve di ogni attack.
         * Printing state of battles:
         * ~~~ Battle nr.x ~~~
         *  Creature i attacks creature j
         *  ...
         *  Creature j is destroyed
         * ~~~ End of battle nr.x ~~~
         **/
        System.out.println("\n");
        for(Attack atk: AttackList.attacks) {
            System.out.println("~~~o~~~ Resolving Battle nr." + (++i) + " ~~~o~~~");
            atk.resolve();
            System.out.println("~~~o~~~ End of battle nr." + i + " ~~~o~~~\n");
        }
        // Resets the AttackList, clearing every declaration of attack and defense
        AttackList.reset();
    }


    private void respond(Player active_player){
        int passes = 0;

        while (passes < 2){
            if (!playIstants(active_player)) passes++;
            if (active_player == current_adversary)
                active_player = current_player;
            else
                active_player = current_adversary;
        }
    }


    private boolean playIstants(Player active_player){
        ArrayList<Effect> available_effects = new ArrayList<>();
        Scanner reader = CardGame.instance.get_scanner();

        //...cards first
        System.out.println(active_player.get_name() + " select effect/istant to play, 0 to pass");
        int i=0;
        for( Card c: active_player.get_hand() ) {
            if ( c.isInstant() ) {
                available_effects.add(c.get_effect(active_player));
                System.out.println(Integer.toString(i+1)+") "+c);
                i++;
            }
        }
        //...creature effects last
        for ( Creature c:active_player.get_creatures()) {
            for (Effect e:c.avaliable_effects()) {
                if (!c.isTapped())
                    available_effects.add(e);
                    System.out.println(Integer.toString(i+1)+") " + c.name() + " ["+ e + "]" );
                i++;
            }
        }
        //get user choice and play it
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=available_effects.size()) return false;
        available_effects.get(idx).play();
        return true;
    }

}

