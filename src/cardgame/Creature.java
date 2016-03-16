package cardgame;

import java.util.List;

public interface Creature {
    String name();
    boolean tap();
    boolean untap();
    boolean isTapped();
    void attack();
    void defend(Creature c);
    void inflict_damage(int dmg);
    void reset_damage();
    int get_power();
    int get_toughness();
    
    // returns all the effects associated to this creature
    List<Effect> effects();
    
    // returns only the effects that can be played currently
    // depending on state, e.g., tapped/untapped
    List<Effect> avaliable_effects();
}
