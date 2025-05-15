package Rule;

import Core.Card;

public interface Rules {
    boolean isPlayable(Card cardToPlay, Card topCardOnPile);
}