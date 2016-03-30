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

    public void getNextDefender(){

    }

    public void resolve(){

    }
}
