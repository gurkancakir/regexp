package com.luxoft.regexp.engine;

import com.luxoft.regexp.engine.core.Step;
import lombok.AllArgsConstructor;
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

    private final List<Step> steps;


    public Matcher compile(String pattern) {
        Matcher matcher = new Matcher(new Stack<>());
        int lastIndex = pattern.length();
        for (int i = pattern.length() - 1; i >= 0; i--) {
            for (Step step : steps) {
                if (step.isAcceptable(pattern.charAt(i))) {
                    Step current = step.create(step.split(pattern, i, lastIndex));
                    lastIndex = i - 1;
                    matcher.add(current);
                }
            }
        }
        return matcher;
    }
}
