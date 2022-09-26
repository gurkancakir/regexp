package com.luxoft.regexp.engine.core;

import com.luxoft.regexp.engine.response.MatchResponse;
import com.luxoft.regexp.engine.response.SplitResponse;

public interface RegexStep {

    boolean isAcceptable(char ch);
    RegexStep create(String pattern);
    MatchResponse matches(String text);

    default SplitResponse split(String pattern, int index) {
        SplitResponse splitResponse = new SplitResponse();
        splitResponse.setSplitted(pattern.substring(index - 1));
        splitResponse.setRemaining(pattern.substring(0, index - 1));
        return splitResponse;
    }
}
