package com.luxoft.regexp;

import com.luxoft.regexp.engine.Matcher;
import com.luxoft.regexp.engine.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternMatcherTest {

    @Autowired
    private Pattern pattern;

    private static Stream<Arguments> provideRegex() {
        return Stream.of(
                Arguments.of("g...an", "gurkan", true),
                Arguments.of("g...an", "gurk", false),
                Arguments.of("g?urkan", "urkan", true),
                Arguments.of("g?urkan", "gurkan", true),
                Arguments.of("g?urkan", "ggurkan", false),
                Arguments.of("lux*oft", "luoft", true),
                Arguments.of("lux*oft", "luxoft", true),
                Arguments.of("lux*oft", "luxxxxxxoft", true),
                Arguments.of("a*bcd?xp gu...n[abc]4+55", "abcdxp gurkanb4444444455", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRegex")
    public void testPatternMatcher(String strPattern, String inputText, boolean expected) {
        Matcher matcher = pattern.compile(strPattern);
        boolean result = matcher.matches(inputText);
        assertEquals(expected, result);
    }
}
