package com.luxoft.regexp.engine;

public interface Step {

    boolean isAcceptable(char ch);
    Step create(String pattern);
    MatchResponse matches(String text);
}
