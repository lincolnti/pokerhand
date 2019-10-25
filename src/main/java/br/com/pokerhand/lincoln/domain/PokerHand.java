package br.com.pokerhand.lincoln.domain;

import br.com.pokerhand.lincoln.enumerateds.Result;
import br.com.pokerhand.lincoln.exceptions.InvalidCardException;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class PokerHand {

    private String card;

    public PokerHand(String card) {
        this.card = card;
    }

    public Result compareWith(PokerHand anotherPokerHand) throws InvalidCardException {
        List<String> pokerHand1 = Arrays.asList(getCard().split(" "));
        List<String> pokerHand2 = Arrays.asList(anotherPokerHand.getCard().split(" "));

        validate(pokerHand2);

        return getResult(getCategory(pokerHand1), getCategory(pokerHand2));
    }

    public Result tiebreakerWith(PokerHand anotherPokerHand) {
        List<String> pokerHand1 = Arrays.asList(getCard().split(" "));
        List<String> pokerHand2 = Arrays.asList(anotherPokerHand.getCard().split(" "));
        AtomicReference<Result> result = new AtomicReference<>(Result.DRAW);

        OptionalInt index = IntStream
                .range(0, pokerHand1.size() - 1)
                .filter(idx ->  {
                    String hand1 = Category.getValue(pokerHand1.get(idx));
                    String hand2 = Category.getValue(pokerHand2.get(idx));

                    return !hand1.equalsIgnoreCase(hand2);
                })
                .findFirst();

        if (index.isPresent()) {
            String hand1 = Category.getValue(pokerHand1.get(index.getAsInt()));
            String hand2 = Category.getValue(pokerHand2.get(index.getAsInt()));
            result.set(getResult(getHandValue(hand1), getHandValue(hand2)));
        }

        return result.get();
    }

    public String getCard() {
        return card;
    }

    private Result getResult(Integer pokerHand1, Integer pokerHand2) {
        if (pokerHand1 > pokerHand2) {
            return Result.WIN;
        } else if (pokerHand1 < pokerHand2) {
            return Result.LOSS;
        }
        return Result.DRAW;
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

    private Integer getHandValue(String value) {
        switch (value.toUpperCase()) {
            case "A":
                return 13;
            case "K":
                return 12;
            case "Q":
                return 11;
            case "J":
                return 10;
            case "T":
                return 9;
            case "9":
                return 8;
            case "8":
                return 7;
            case "7":
                return 6;
            case "6":
                return 5;
            case "5":
                return 4;
            case "4":
                return 3;
            case "3":
                return 2;
            default:
                return 1;
        }
    }
}
