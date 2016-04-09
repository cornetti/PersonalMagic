package cardgame;

import java.util.ArrayList;

public class Attack {
    private int powerLeft;
    private Player adversary;
    private Creature attacker;
    private ArrayList<Creature> defenders;
    private boolean cancelled;

    public Attack(Creature attacker, Player adv){
        this.adversary = adv;
        this.attacker = attacker;
        this.powerLeft = attacker.get_power();
        this.defenders = new ArrayList<Creature>();
    }

    public int getDmg() {
        return powerLeft;
    }

    public ArrayList<Creature> getDefenders(){
        return this.defenders;
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

    public boolean isCancelled(){
        //verifica che l'attacco non sia stato annullato
        return cancelled;
    }

    public void cancel(){
        //annulla l'attacco
        cancelled = true;
    }

    /* Metodo bellissimo che però non ci serve più :'(

    // Restituisce il prossimo defender da attaccare.
    //Dovevamo usarlo per fare in modo che il prossimo difensore difendesse l'attacco che avanzava :'(
    public Creature getNextDefender(){
        if(!defenders.isEmpty())
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
    } */



    public void resolve(){
        if (!isCancelled()) {
            // atkToDeal: l'attacco che l'attaccante infliggerà al difensore (giocatore)
            // atkToReceive: l'attacco totale che l'attaccante riceverà dai suoi difensori
            // toughness: l'attacco del attaccante, decrementerà dopo gli attacchi inflitti
            int atkToDeal, atkToReceive = 0;
            int toughness = attacker.get_toughness();
            powerLeft = attacker.get_power(); //va bene fin là (esempio le carte che dimezzano gli attacchi non funzionerebbero)

            // Il defender contro il quale sta combattendo attualmente
            // Se l'attaccante attuale non ha nessun defender allora attacca diretamente.
            Creature actualDef = (defenders.isEmpty() ? null : defenders.get(0));
            Creature old;

            if(actualDef == null) {
                System.out.println(attacker.name() + "(" + attacker.getOwner().get_name() + ") attacks directly " + adversary.get_name());
                adversary.inflict_damage(powerLeft);
            }
            while (actualDef != null && powerLeft > 0) {
                // Calcolo del danno da infliggere al attuale difensore.
                atkToDeal = (powerLeft > actualDef.get_toughness() ? actualDef.get_toughness() : powerLeft);
                powerLeft -= atkToDeal;
                // Incrementa l'attacco totale che l'attaccante riceverà.
                atkToReceive += (actualDef.get_power() > toughness ? toughness : actualDef.get_power());

                old = actualDef; // Mi salvo questo difensore.
                defenders.remove(0); // Lo rimuovo dalla lista
                actualDef = (defenders.isEmpty() ? null : defenders.get(0)); // Prendo il prossimo difensore.
                System.out.println(attacker.name() + "(" + attacker.getOwner().get_name() + ") deals " + atkToDeal + " damage to " + old.name() + "(" + old.getOwner().get_name() + ")");
                old.inflict_damage(atkToDeal); // Infliggo danno al difensore precedente che potrebbe morire causando problemi alla lista.
            }
            // L'attacco che i difensori infliggono all'attaccante. Metto il controllo IF perché
            //  non mi piace attivare il metodo inutilmente se il danno è 0.
            attacker.tap();
            if(atkToReceive > 0)
                attacker.inflict_damage(atkToReceive);
        }
    }
}
