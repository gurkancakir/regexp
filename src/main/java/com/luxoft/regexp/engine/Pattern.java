package com.luxoft.regexp.engine;

import com.luxoft.regexp.engine.core.RegexStep;
import com.luxoft.regexp.engine.response.SplitResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Component
@AllArgsConstructor
public class Pattern {

    /*
        [abc] – character from a set
        . – any character
        ? – zero or one time
        * - zero or more times
        + - one or more times
     */

    private final List<RegexStep> regexSteps;

    @Qualifier("lengthMatcher")
    private final RegexStep lengthMatcher;

    @Qualifier("stringMatcher")
    private final RegexStep stringMatcher;

    public Matcher compile(String pattern) {
        String currentPattern = pattern;
        Stack<RegexStep> stack = new Stack<>();
        Matcher matcher = new Matcher(stack);
        matcher.add(lengthMatcher.create(""));

        int index = currentPattern.length() - 1;
        while (index >= 0) {
            for (RegexStep regexStep : regexSteps) {
                if (regexStep.isAcceptable(currentPattern.charAt(index))) {
                    SplitResponse splitResponse = regexStep.split(currentPattern, index);
                    currentPattern = splitResponse.getRemaining();
                    index = currentPattern.length();
                    RegexStep current = regexStep.create(splitResponse.getSplitted());
                    matcher.add(current);
                    break;
                }
            }
            index--;
        }
        if (!currentPattern.isEmpty()) {
            matcher.add(stringMatcher.create(currentPattern));
        }
        return matcher;
    }
}
