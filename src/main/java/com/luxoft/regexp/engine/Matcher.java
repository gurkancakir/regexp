package com.luxoft.regexp.engine;

import com.luxoft.regexp.engine.core.Step;
import com.luxoft.regexp.engine.response.MatchResponse;
import lombok.AllArgsConstructor;

import java.util.Stack;

@AllArgsConstructor
public class Matcher {
    private Stack<Step> steps;

    public void add(Step step) {
        steps.add(step);
    }

    public boolean matches(String text) {
        int index = 0;
        while (!steps.isEmpty()) {
            Step step = steps.pop();
            MatchResponse response = step.matches(text.substring(index));
            System.out.printf("%-90.90s  %-90.90s%n", step, response);
            if (!response.getResult())
                return false;
            index += response.getIndex();
        }
        return true;
    }
}
