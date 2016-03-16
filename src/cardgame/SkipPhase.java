package cardgame;


public class SkipPhase implements Phase {
    private final Phases phase_id;
    private int skip_num;
    
    public SkipPhase(Phases id) {
        phase_id=id;
        skip_num=1;
    }
    public SkipPhase(Phases id, int skip) {
        phase_id=id;
        skip_num=skip;
    }
    
    
    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        System.out.println(current_player.get_name() + ": skip " + phase_id.get_name() +" phase");
        --skip_num;
        if (skip_num==0) current_player.remove_phase(phase_id,this);
    }
}