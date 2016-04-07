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

    /* Metodo bellissimo che però non ci serve più :'(

    // Restituisce il prossimo defender da attaccare.
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
        // atkToDeal: l'attacco che l'attaccante infliggerà al difensore (giocatore)
        // atkToReceive: l'attacco totale che l'attaccante riceverà dai suoi difensori
        // toughness: l'attacco del attaccante, decrementerà dopo gli attacchi inflitti
        int atkToDeal, atkToReceive = 0;
        int toughness = attacker.get_toughness();

        // Il defender contro il quale sta combattendo attualmente
        // Se l'attaccante attuale non ha nessun defender allora attacca diretamente.
        Creature actualDef = (defenders.isEmpty() ? null : defenders.get(0));

        if(actualDef == null)
            adversary.inflict_damage(powerLeft);
        while(actualDef != null && powerLeft > 0){
            // Calcolo del danno da infliggere al attuale difensore.
            atkToDeal = (powerLeft > actualDef.get_toughness() ? actualDef.get_toughness() : powerLeft);
            // Incrementa l'attacco totale che l'attaccante riceverà.
            atkToReceive += (actualDef.get_power() > toughness ? toughness : actualDef.get_power());

            actualDef.inflict_damage(atkToDeal);
            // Il prossimo difensore sarà:
            //  -Se quello in posizione 0 non è morto -> actualDef = defenders in posizione 1;
            //  -Se quello in posizione 0 è morto -> actualDef = defenders in posizione 0 (sarà il nuovo difensore);
            actualDef = (defenders.get(0).equals(actualDef) ? defenders.get(1) : defenders.get(0));
        }
        // L'attacco che i difensori infliggono all'attaccante. Metto il controllo IF perché
        //  non mi piace attivare il metodo inutilmente se il danno è 0.
        if(atkToReceive > 0)
            attacker.inflict_damage(atkToReceive);
        attacker.tap();
    }
}
