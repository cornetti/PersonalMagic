package cardgame;

import cardgame.cards.*;

import java.util.*;

/*
Signleton class maintaining global game properties.
Handles: 
 - Players
 - Turns
 - Stack
 - Triggers
*/
public class CardGame {


    public static void main(String[] args) {

        //create decks
        ArrayList<Card> deck = new ArrayList<Card>();
        //for (int i=0; i<2; i++) deck.add(new Homeopathy());          //tested
        for (int i=0; i<3; ++i) deck.add(new Reflexologist());      //tested
        //for (int i=0;i<2; i++) deck.add(new Cancel());               //tested
        for (int i=0;i<3; i++) deck.add(new NorwoodRanger());        //tested
        for (int i=0;i<3; i++) deck.add(new BronzeSable());          //tested
        //for (int i=0;i<2; i++) deck.add(new Afflict());              //tested
        //for (int i=0;i<2; i++) deck.add(new AggressiveUrge());       //tested
        //for (int i=0;i<2; i++) deck.add(new BenevolentAncestor());   //tested
        //for (int i=0;i<2; i++) deck.add(new BoilingEarth());         //tested
        //for (int i=0;i<2; i++) deck.add(new Darkness());             //tested
        //for (int i=0;i<4; i++) deck.add(new DayOfJudgement());
        for (int i=0;i<5; i++) deck.add(new Deflection());
        //for (int i=0;i<2; i++) deck.add(new FalsePeace());
        //for (int i=0;i<2; i++) deck.add(new Fatigue());
        //for (int i=0;i<2; i++) deck.add(new SavorTheMoment());
        for (int i=0;i<5; i++) deck.add(new VolcanicHammer());       //tested
        //for (int i=0;i<2; i++) deck.add(new WorldAtWar());


        Collections.shuffle(deck);

        instance.get_player(0).set_deck(deck.iterator());
        instance.get_player(1).set_deck(deck.iterator());

        instance.run();
    }

    //Signleton and instance access
    public static final CardGame instance = new CardGame();


    //game setup
    private CardGame() {
        turn_manager_stack.push( new DefaultTurnManager(Players) );

        Players[0]=new Player();
        Players[0].set_name("Player 1");
        Players[0].set_phase(Phases.DRAW,new SkipPhase(Phases.DRAW));


        Players[1]=new Player();
        Players[1].set_name("Player 2");
    }

    //execute game
    public void run() {
        Players[0].get_deck().shuffle();
        Players[1].get_deck().shuffle();

        for (int i=0; i!=5; ++i) Players[0].draw();
        for (int i=0; i!=5; ++i) Players[1].draw();

        try {
            while (true) { instance.next_player().execute_turn(); }
        } catch(EndOfGame e)
        {
            System.out.println(e.getMessage());
        }
    }


    // Player and turn management
    private final Player[] Players = new Player[2];
    private final Deque<TurnManager>  turn_manager_stack = new ArrayDeque<TurnManager>();
    public void set_turn_manager(TurnManager m) { turn_manager_stack.push(m); }
    public void remove_turn_manager(TurnManager m) { turn_manager_stack.remove(m); }

    Player get_player(int i) { return Players[i]; }
    public Player get_current_player() { return turn_manager_stack.peek().get_current_player(); }
    public Player get_current_adversary() { return turn_manager_stack.peek().get_current_adversary(); }
    Player next_player() { return turn_manager_stack.peek().next_player(); }


    // Stack access
    private CardStack stack = new CardStack();
    public CardStack get_stack() { return stack; }


    //Trigger access
    private Triggers triggers=new Triggers();
    public Triggers get_triggers() { return triggers; }


    //IO resources  to be dropped in final version
    Scanner reader = new Scanner(System.in);
    Scanner get_scanner() { return reader; }
}
