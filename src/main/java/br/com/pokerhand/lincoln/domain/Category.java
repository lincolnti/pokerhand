package br.com.pokerhand.lincoln.domain;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Category {

    public static Boolean isRoyalFlush(List<String> cards) {
        List<String> suits = cards.stream().map(s -> getSuit(s)).collect(Collectors.toList());
        Boolean hasA = cards.stream().anyMatch(card -> card.startsWith("A"));
        Boolean hasK = cards.stream().anyMatch(card -> card.startsWith("K"));
        Boolean hasQ = cards.stream().anyMatch(card -> card.startsWith("Q"));
        Boolean hasJ = cards.stream().anyMatch(card -> card.startsWith("J"));
        Boolean hasT = cards.stream().anyMatch(card -> card.startsWith("T"));
        return hasA && hasK && hasQ && hasJ && hasT && isSameSuit(suits);
    }

    public static Boolean isStraightFlush(List<String> cards) {
        List<String> suits = cards.stream().map(card -> getSuit(card)).collect(Collectors.toList());
        int[] filtered = cards.stream().filter(card -> isNumber(card)).mapToInt(card -> Integer.valueOf(getValue(card))).toArray();
        return isConsecutive(filtered) && isSameSuit(suits);
    }

    public static Boolean isFourOfAKind(List<String> cards) {
        if (isSameValue(cards.get(0), cards, 4)) {
            return true;
        } else {
            return isSameValue(cards.get(1), cards, 4);
        }
    }

    public static Boolean isFullHouse(List<String> cards) {
        List<String> strings = new ArrayList<>(new HashSet<>(cards));
        Boolean threeOfAKind = isSameValue(strings.get(0), cards, 3);
        Boolean pair = isSameValue(strings.get(1), cards, 2);
        Boolean valid = threeOfAKind && pair;

        if (!valid) {
            threeOfAKind = isSameValue(strings.get(1), cards, 3);
            pair = isSameValue(strings.get(0), cards, 2);
            valid = threeOfAKind && pair;
        }

        return valid;
    }

    public static Boolean isFlush(List<String> cards) {
        return isSameSuit(cards.stream().map(s -> getSuit(s)).collect(Collectors.toList()));
    }

    public static Boolean isStraight(List<String> cards) {
        Boolean isDifferentSuit = IntStream.range(0, cards.size())
                .filter(idx -> idx+1 != cards.size()
                        && getSuit(cards.get(idx)).equalsIgnoreCase(getSuit(cards.get(idx+1)))).count() == 0;

        int[] filtered = cards.stream().filter(card -> isNumber(card)).mapToInt(card -> Integer.valueOf(getValue(card))).toArray();

        return isConsecutive(filtered) && isDifferentSuit;
    }

    public static Boolean isThreeOfAKind(List<String> cards) {
        Boolean threeOfAKind = cards.stream().anyMatch(card -> isSameValue(card, cards, 3));
        Boolean twoNotRelated = cards.stream().filter(card -> isSameValue(card, cards, 1)).count() == 2;
        return threeOfAKind && twoNotRelated;
    }

    public static Boolean isTwoPair(List<String> cards) {
        return cards.stream().map(card -> getValue(card)).collect(Collectors.toSet())
                .stream()
                .filter(card -> isSameValue(card, cards, 2))
                .count() == 2;
    }

    public static Boolean isPair(List<String> cards) {
        System.out.println();
        return cards.stream().map(card -> getValue(card)).collect(Collectors.toSet())
                .stream()
                .filter(card -> isSameValue(card, cards, 2))
                .count() == 1;
    }

    private static boolean isConsecutive(int... numbers) {
        Arrays.sort(numbers);

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] != numbers[i-1] + 1) {
                return false;
            }
        }

        return true;
    }

    private static Boolean isNumber(String card) {
        try {
            Integer.valueOf(getValue(card));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Boolean isSameSuit(List<String> cards) {
        return cards.stream().distinct().limit(5).count() <= 1;
    }

    private static Boolean isSameValue(String value, List<String> cards, Integer number) {
        AtomicInteger atomicInteger = new AtomicInteger();

        cards.forEach(card -> {
            if (getValue(card).equalsIgnoreCase(getValue(value))) {
                atomicInteger.incrementAndGet();
            }
        });
        return atomicInteger.get() == number;
    }

    private static String getSuit(String value) {
        if (value.length() == 3) {
            return value.substring(2, 3);
        } else {
            return value.substring(1, 2);
        }
    }

    public static String getValue(String value) {
        if (value.length() == 3) {
            return value.substring(0, 2);
        } else {
            return value.substring(0,1);
        }
    }
}
