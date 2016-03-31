package cardgame;

import java.util.ArrayList;

public class Attack {
    private int powerLeft;
    private Player adversary;
    private Creature attacker;
    private ArrayList<Creature> defenders;

    public Attack(Creature attacker, Player adv){
        this.adversary = adv;
        this.attacker = attacker;
        this.powerLeft = attacker.get_power();
        this.defenders = null;
    }

    public int getDmg() {
        return powerLeft;
    }

    public void setDmg(int dmg) {
        this.powerLeft = dmg;
    }

    public void addDefender(Creature cr){
        defenders.add(cr);
    }

    public Creature getAttacker(){
        return this.attacker;
    }

    // Restituisce il prossimo defender da attaccare.
    public Creature getNextDefender(){
        if(defenders.isEmpty() == false)
            return defenders.get(0);
        else { // Se i defender di questo attaccante sono finiti allora si restituisce
               //  il prossimo defender del prossimo attacker.
            ArrayList<Attack> lst = AttackList.attacks;
            int index = lst.indexOf(this); // index del attack in qui mi trovo
            while (index < lst.size()) // finché ci sono attack (attacchi dichiarati)
                // se nel prossimo attack non ci sono defender cerco in quello successivo
                if (lst.get(index + 1).defenders.isEmpty())
                    index++;
                else {
                    // altrimenti prendo il primo defender del prossimo attack
                    defenders = lst.get(index + 1).defenders;
                    return defenders.get(0);
                }
        }
        return null;
    }

    public void resolve(){
        // Il defender contro il quale sta combattendo attualmente
        // Se l'attaccante attuale non ha nessun defender allora attacca diretamente.
        Creature actualDef = (defenders.isEmpty() ? null : defenders.get(0));
        int atkToDeal; // Attacco effettivo da infliggere al defender
        while(actualDef != null && powerLeft > 0){ // finché ci sono difensori o attacco da infligere
            // calcolo del attacco effettivo da infliggere
            atkToDeal = (powerLeft > actualDef.get_toughness() ? actualDef.get_toughness() : powerLeft);
            powerLeft -= atkToDeal;
            actualDef.inflict_damage(atkToDeal);

            attacker.inflict_damage(actualDef.get_power()); // attacco del defender nei confronti del attacker

            // Calcola prossimo defender (Aggiungo l'IF per evitare calcoli inutili)
            if(actualDef == null)
                actualDef = getNextDefender();
        }
        // quando non ci sono defender e l'attacker è ancora vivo e ha ancora attacco
        if(attacker != null && attacker.getDamage_left() > 0)
            adversary.inflict_damage(attacker.getDamage_left());
    }
}
