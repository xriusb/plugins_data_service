package com.xriusb.pluginsdataservice.service.viewcode;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.IntPredicate;

@Component
public class AlphanumericViewCode implements ViewCode<String> {
    int ascIIZeroNumberValue = 48;
    int ascIIZLetterValue = 122;
    int generatedStringLength = 16;
    IntPredicate onlyAscIINumbersAndLetters = i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97);
    Random random = new Random();
    Set<String> usedViewCodes = new HashSet<>();

    @Override
    public String generate() {
        String generatedViewCode = getViewCode();
        while(usedViewCodes.contains(generatedViewCode)) {
            generatedViewCode = getViewCode();
        }
        usedViewCodes.add(generatedViewCode);
        return generatedViewCode;
    }

    private String getViewCode() {
        return random.ints(ascIIZeroNumberValue, ascIIZLetterValue + 1)
                .filter(onlyAscIINumbersAndLetters)
                .limit(generatedStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
