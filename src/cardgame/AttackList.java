package cardgame;

import java.util.ArrayList;

/**
 * Created by mryolo on 31/03/16.
 */
public class AttackList {
    public static ArrayList<Creature> lastAttack = new ArrayList<Creature>();
    public static ArrayList<Attack> attacks = new ArrayList<>();

    public static void add(Attack a){
        attacks.add(a);
        lastAttack.add(a.getAttacker());
    }
    public static void cancel(int i) {
        attacks.get(i).cancel();
    }
    public boolean isEmpty() {
        return attacks.isEmpty();
    }
    public static void reset(){
        attacks = new ArrayList<>();
        CardGame.instance.get_triggers().register(16, new TriggerAction() {
            @Override
            public void execute() {
                lastAttack.clear();
            }
        });
    }

}
