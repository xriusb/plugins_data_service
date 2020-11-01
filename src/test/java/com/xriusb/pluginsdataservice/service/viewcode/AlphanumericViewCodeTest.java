package com.xriusb.pluginsdataservice.service.viewcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlphanumericViewCodeTest {

    private ViewCode<String> alphanumericViewCode = new AlphanumericViewCode();

    @Test
    void whenGenerateAlreadySendCode_ThenGenerateNewOne() {
        String initialCode = alphanumericViewCode.generate();
        assertNotEquals(initialCode, alphanumericViewCode.generate());
    }

    @Test
    void generatedStringOnlyContainsNumbersAndLetters() {
        assertTrue(alphanumericViewCode.generate().matches("^[a-zA-Z0-9]*$"));
    }

    @Test
    void generatedStringHas16CharactersLenght() {
        assertTrue(alphanumericViewCode.generate().length() == 16);
    }


}