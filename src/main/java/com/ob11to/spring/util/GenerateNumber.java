package com.ob11to.spring.util;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

//@UtilityClass
public final class GenerateNumber {

    private GenerateNumber() {
        throw new RuntimeException("Don't let anyone instantiate this class.");
    }

    private static String lastNumber = "A000AA";
    private static final String ZERO = "000";
    private static final TreeSet<String> allNumbers = new TreeSet<>();
    private static final String OVERFLOW = "NUMBERS IS OVERFLOW";
    private static final String[] litters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
    private static final String REGION = "161 RUS";
    private static final short MAX_VALUE = 1000;

    public static String getRandomPlateNumber() {
        String result = generateRandom();
        if (!allNumbers.contains(result)) {
            allNumbers.add(result);
        } else if (result.equals(OVERFLOW)) {
            throw new RuntimeException("Sorry, but generate new number is impossible");
        } else {
            getRandomPlateNumber();
        }
        return result + " " + REGION;
    }

    private static String generateRandom() {
        Random random = new Random();
        var firstLitters = litters[random.nextInt(litters.length - 1)];
        var n = random.nextInt(MAX_VALUE);
        String number;
        if (n < MAX_VALUE / 100) {
            number = "00" + n;
        } else if (n < MAX_VALUE / 10) {
            number = "0" + n;
        } else {
            number = "" + n;
        }
        var secondLitters = litters[random.nextInt(litters.length - 1)];
        var thirdLitter = litters[random.nextInt(litters.length - 1)];

        return firstLitters + number + secondLitters + thirdLitter;
    }

    public static String getNextPlateNumber() {
        String result = generateNextPlateNumber();
        if (!allNumbers.contains(result)) {
            allNumbers.add(result);
        } else if (result.equals(OVERFLOW)) {
            throw new RuntimeException("Sorry, but generate new number is impossible");
        } else {
            getNextPlateNumber();
        }
        return result + " " + REGION;
    }

    private static String generateNextPlateNumber() {
        String result = lastNumber;
        if (allNumbers.size() > 0) {
            String firstLitter = lastNumber.substring(0, 1);
            String numbers = lastNumber.substring(1, 4);
            String secondLitter = lastNumber.substring(4, 5);
            String thirdLitter = lastNumber.substring(5, 6);
            short preSum = (short) (Short.parseShort(numbers) + 1);
            String prefix;
            if (preSum <= MAX_VALUE - 1) {
                if (preSum < MAX_VALUE / 100) {
                    prefix = "00";
                } else if (preSum < MAX_VALUE / 10) {
                    prefix = "0";
                } else {
                    prefix = "";
                }
                result = firstLitter + prefix + (preSum) + secondLitter + thirdLitter;
            } else {
                if (!thirdLitter.equals(litters[litters.length - 1])) {
                    var index = Arrays.asList(litters).indexOf(thirdLitter);
                    result = firstLitter + ZERO + secondLitter + litters[index + 1];
                } else {
                    if (!secondLitter.equals(litters[litters.length - 1])) {
                        var index = Arrays.asList(litters).indexOf(secondLitter);
                        result = firstLitter + ZERO + litters[index + 1] + litters[0];
                    } else {
                        if (!firstLitter.equals(litters[litters.length - 1])) {
                            int index = Arrays.asList(litters).indexOf(firstLitter);
                            result = litters[index + 1] + ZERO + litters[0] + litters[0];
                        } else {
                            return OVERFLOW;
                        }
                    }
                }
            }
        }
        lastNumber = result;
        return result;
    }
}
