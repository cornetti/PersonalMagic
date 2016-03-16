package cardgame;

import java.util.Deque;
import java.util.EnumMap;


public class DefaultPhaseManager implements PhaseManager{
    private EnumMap<Phases, Deque<Phase> > phases;
    private Phases current_phase_idx=Phases.NULL;
    
    public DefaultPhaseManager(EnumMap<Phases, Deque<Phase> > p) {phases=p;}
    
    
    public Phases current_phase() { return current_phase_idx; }
    
    public Phases next_phase() { 
        current_phase_idx = current_phase_idx.next();
        return current_phase();
    } 
    
    
}
