package cardgame;


import java.util.ArrayList;

// utility class implementing common default behavior and fields for creatures
// creatures with different behavior from the default nee not extend it
public abstract class AbstractCreature implements Creature {
    protected Player owner;
    protected boolean is_tapped=false;
    protected int damage_left = get_toughness();
    protected int power;
    protected int toughness;

    protected AbstractCreature(Player owner) { this.owner=owner; }

    public Player getOwner(){return this.owner;}
        
    public boolean tap() {
        if (is_tapped) {
            System.out.println("creature " + name() + " already tapped");
            return false;
        }

        System.out.println("tapping creature " + name() + "(" + getOwner().get_name() + ")");
        is_tapped=true;
        return true;
    }

    public boolean untap() {
        if (!is_tapped) {
            System.out.println("creature " + name() + " not tapped");
            return false;
        }

        System.out.println("untapping creature " + name() + "(" + getOwner().get_name() + ")");
        is_tapped=false;
        return true;
    }

    public boolean isTapped() { return is_tapped; }

    public void attack() {
        Attack atk = new Attack(this, CardGame.instance.get_current_adversary());
        AttackList.add(atk);
        // Daffinireddopo
    } // to do in assignment 2

    public void defend(Creature c) {
        ArrayList<Attack> lst = AttackList.attacks;
        for(Attack atk: lst)
            if(atk.getAttacker().equals(c))
                atk.addDefender(this);
    } // to do in assignment 2

    public void receive(Attack atk){}

    public void calculate_damage(){

    }

    public int getDamage_left(){
        return this.damage_left;
    }

    public void inflict_damage(int dmg) {
        damage_left -= dmg;
        System.out.println(name() + " (" + owner.get_name() + ") receives " + dmg + " damage.");
        if (damage_left<=0){
            System.out.println(name() + " (" + owner.get_name() + ") is destroyed!");
            owner.destroy(this);
        }
    }

    @Override
    public void weaken(int dmg) {
        this.power = power - dmg;
    }

    public void reset_damage() { damage_left = get_toughness(); }

    public int getPower(){ return this.power;}

    public int getToughness(){ return this.toughness;}
    
}
