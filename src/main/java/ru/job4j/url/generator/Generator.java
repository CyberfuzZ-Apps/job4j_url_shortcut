package ru.job4j.url.generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Класс Generator
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class Generator {

    public static String generateRandomString() {
        String numbers =  new Random().ints(5, 48, 57)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
        String upperCase =  new Random().ints(5, 65, 90)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
        String lowerCase =  new Random().ints(5, 97, 122)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
        StringBuilder stringBuilder = new StringBuilder(numbers)
                .append(upperCase)
                .append(lowerCase);
        List<String> elements = Arrays.asList(stringBuilder.toString().split(""));
        Collections.shuffle(elements);
        stringBuilder.setLength(0);
        for (String element : elements) {
            stringBuilder.append(element);
        }
        return stringBuilder.toString();
    }

}
