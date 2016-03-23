package cardgame;

import java.util.ArrayList;

// utility clas implementing common defaul behavior and fields for creatures
// creatures with differenf behavior from the default nee not extend it
public abstract class AbstractCreature implements Creature {
    protected Player owner;
    protected boolean is_tapped=false;
    protected int damage_left = get_toughness();
    protected ArrayList<Attack> attackList;

    protected AbstractCreature(Player owner) { this.owner=owner; }
        
        public boolean tap() { 
            if (is_tapped) {
                System.out.println("creature " + name() + " already tapped");
                return false;
            }
            
            System.out.println("tapping creature " + name());
            is_tapped=true; 
            return true; 
        }
        
        public boolean untap() { 
            if (!is_tapped) {
                System.out.println("creature " + name() + " not tapped");
                return false;
            }
            
            System.out.println("untapping creature " + name());
            is_tapped=false; 
            return true; 
        }
        
        public boolean isTapped() { return is_tapped; }
        public void attack(Creature c, int attack) {
            Attack dmg = new Attack(attack);
            attackList.add(dmg);
            c.receive(dmg);
            // Dafiniredopo
        } // to do in assignment 2
        public void defend(Creature c) {} // to do in assignment 2

        public void receive(Attack atk){
            attackList.add(atk);
        }

        public void calculate_damage(){
            for(Attack a: attackList)
                inflict_damage(a.getDmg());
        }

        public void inflict_damage(int dmg) { 
            damage_left -= dmg; 
            if (damage_left<=0)
                owner.destroy(this);        
        }
        
        public void reset_damage() { damage_left = get_toughness(); }
    
}
