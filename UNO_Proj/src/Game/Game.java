package Game;

import Core.*;
import Player.Player;
import Rule.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    protected List<Player> players;
    protected Deck deck;
    protected Rules rule;

    public Game(Rules rule) {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.rule = rule;
    }

    // Template method — controls game flow
    public final void play() {
        initializeGame();
        while (!isGameOver()) {
            playTurn();
        }
        System.out.println("🎉 Game Over!");
    }

    // To be implemented by each variation
    protected abstract void initializeGame();
    protected abstract void playTurn();
    protected abstract boolean isGameOver();
}
