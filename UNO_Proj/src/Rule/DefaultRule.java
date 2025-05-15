package Rule;

import Core.Card;
import Core.*;

public class DefaultRule implements Rules {
    @Override
    public boolean isPlayable(Card cardToPlay, Card topCardOnPile) {
        return cardToPlay.getColor() == topCardOnPile.getColor()
                || cardToPlay.getCardType() == topCardOnPile.getCardType()
                || cardToPlay.getColor() == Color.BLACK; // wild
    }

}
