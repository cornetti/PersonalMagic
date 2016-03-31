package cardgame;

import java.util.ArrayList;

public class Attack {
    private int dmg;
    private Player adversary;
    private Creature attacker;
    private ArrayList<Creature> defenders;

    public Attack(Creature attacker, Player adv){
        this.adversary = adv;
        this.attacker = attacker;
        this.dmg = attacker.get_power();
        this.defenders = null;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void addDefender(Creature cr){
        defenders.add(cr);
    }

    public Creature getAttacker(){
        return this.attacker;
    }

    public Creature getNextDefender(){
        if(defenders.isEmpty() != true)
            return defenders.get(0);
        else {
            ArrayList<Attack> lst = AttackList.attacks;
            int index = lst.indexOf(this);
            while (index < lst.size())
                if (lst.get(index + 1).defenders.isEmpty())
                    index++;
                else {
                    defenders = lst.get(index + 1).defenders;
                    return defenders.get(0);
                }
        }
        return null;
    }

    public void resolve(){
        Creature actualDef = getNextDefender();
        int powerLeft = attacker.get_power();
        int atkToDeal;
        while(actualDef != null && attacker.getDamage_left() > 0){

            atkToDeal = (powerLeft > actualDef.get_toughness() ? actualDef.get_toughness() : powerLeft);
            powerLeft -= atkToDeal;
            actualDef.inflict_damage(atkToDeal);

            attacker.inflict_damage(actualDef.get_power());

            actualDef = getNextDefender();
        }
        if(attacker != null)
            adversary.inflict_damage(attacker.getDamage_left());
    }
}
