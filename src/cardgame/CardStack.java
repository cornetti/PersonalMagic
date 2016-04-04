package cardgame;

import java.util.ArrayDeque;
import java.util.Iterator;


public class CardStack implements Iterable<Effect> {
    private final ArrayDeque<Effect> stack = new ArrayDeque<>();
    
    public Iterator<Effect> iterator() { return stack.iterator(); }
    
    public void add(Effect e) { 
        stack.push(e); 
    }
    
    public void remove(Effect e) { stack.remove(e); }
    
    public void resolve() {
        while(!stack.isEmpty()) { 
            Effect e = stack.pop();
            
            System.out.println("Stack: resolving " + e);
            
            e.resolve(); 
        }
    }

    public Effect get(int index){
        Iterator<Effect> it = iterator();
        Effect e = null;
        for (int i = 0; i<index-1; i++){
            if (it.hasNext()){
                e = it.next();
            }
        }
        return e;
    }
}
