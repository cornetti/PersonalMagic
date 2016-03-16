package cardgame;


public interface Card {

// returns the effect to be placed on teh stack
Effect get_effect(Player owner);
String name();
String type(); //sorcery, instant, or creature
String rule_text();
boolean isInstant();
}
