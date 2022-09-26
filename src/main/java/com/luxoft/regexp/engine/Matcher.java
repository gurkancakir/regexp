package com.luxoft.regexp.engine;

import com.luxoft.regexp.engine.core.RegexStep;
import com.luxoft.regexp.engine.response.MatchResponse;
import lombok.AllArgsConstructor;

import java.util.Stack;

@AllArgsConstructor
public class Matcher {
    private Stack<RegexStep> regexSteps;

    public void add(RegexStep regexStep) {
        regexSteps.add(regexStep);
    }

    public boolean matches(String text) {
        System.out.println("Input : " + text);
        System.out.println("-----------------------------------");
        int index = 0;
        while (!regexSteps.isEmpty()) {
            RegexStep regexStep = regexSteps.pop();
            MatchResponse response = regexStep.matches(text.substring(index));
            System.out.printf("%-70.70s  %-70.70s%n", regexStep, response);
            if (!response.getResult())
                return false;
            index += response.getIndex();
        }
        System.out.println("-----------------------------------\n");
        return true;
    }
}
