package br.com.pokerhand.lincoln.domain;

import br.com.pokerhand.lincoln.enumerateds.Result;
import br.com.pokerhand.lincoln.exceptions.InvalidCardException;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class PokerHand {

    private String card;

    public PokerHand(String card) {
        this.card = card;
    }

    public Result compareWith(PokerHand anotherPokerHand) throws InvalidCardException {
        List<String> pokerHand1 = Arrays.asList(getCard().split(" "));
        List<String> pokerHand2 = Arrays.asList(anotherPokerHand.getCard().split(" "));

        validate(pokerHand2);

        if (getCategory(pokerHand1) > getCategory(pokerHand2)) {
            return Result.WIN;
        } else if (getCategory(pokerHand1) < getCategory(pokerHand2)) {
            return Result.LOSS;
        }
        return Result.DRAW;
    }

    public String getCard() {
        return card;
    }

    private void validate(List<String> cards) throws InvalidCardException {
        if (CollectionUtils.isEmpty(cards)) throw new InvalidCardException("Carta inválida");
    }

    private Integer getCategory(List<String> cards) {
        if (Category.isRoyalFlush(cards)) {
            return 10;
        } else if (Category.isStraightFlush(cards)) {
            return 9;
        } else if (Category.isFourOfAKind(cards)) {
            return 8;
        } else if (Category.isFullHouse(cards)) {
            return 7;
        } else if (Category.isFlush(cards)) {
            return 6;
        } else if (Category.isStraight(cards)) {
            return 5;
        } else if (Category.isThreeOfAKind(cards)) {
            return 4;
        } else if (Category.isTwoPair(cards)) {
            return 3;
        } else if (Category.isPair(cards)) {
            return 2;
        }
        return 1;
    }
}
