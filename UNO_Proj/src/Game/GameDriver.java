package Game;

import Rule.DefaultRule;

public class GameDriver {
    public static void main(String[] args) {
        Game game = new ClassicUnoGame(new DefaultRule());
        game.play();
    }
}