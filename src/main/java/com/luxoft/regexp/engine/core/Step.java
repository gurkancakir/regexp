package com.luxoft.regexp.engine.core;

import com.luxoft.regexp.engine.response.MatchResponse;

public interface Step {

    boolean isAcceptable(char ch);
    Step create(String pattern);
    MatchResponse matches(String text);

    default String split(String pattern, int startIndex, int lastIndex) {
        return pattern.substring(startIndex - 1, lastIndex);
    }
}
