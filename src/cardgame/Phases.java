package cardgame;


public enum Phases { 
    DRAW ("draw"), UNTAP ("untap"), COMBAT ("combat"), MAIN ("main"), END ("end"), NULL ("null");
    private static final Phases[] vals = values();
    private final String name;
    Phases(String n) { this.name=n; }
    public String get_name() { return name; }
    public Phases next() { return vals[(this.ordinal()+1) % vals.length]; }
    public Phases prev() { return vals[(this.ordinal()+vals.length-1) % vals.length]; }
    public int get_idx() { return this.ordinal(); }
    public static Phases idx(int i) { return vals[i]; }
    
    static final int DRAW_FILTER=1;
    static final int UNTAP_FILTER=2;
    static final int COMBAT_FILTER=4;
    static final int MAIN_FILTER=8;
    static final int END_FILTER=16;
}