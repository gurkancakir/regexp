package com.luxoft.regexp.engine.core;

import com.luxoft.regexp.engine.response.MatchResponse;
import com.luxoft.regexp.engine.response.PrefixResponse;

public abstract class AbstractRegexStepMatcher implements RegexStep {


    public abstract String getSuffix();

    protected boolean checkSuffix(String text, int lastIndex) {
        if (text.substring(lastIndex).length() < getSuffix().length())
            return false;
        String input = text.substring(lastIndex, lastIndex + getSuffix().length());
        return getSuffix().equals(input);
    }

    protected PrefixResponse searchPrefix(String text, String prefix) {
        int prefixCount = 0;
        int lastIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            if (prefix.equals(String.valueOf(text.charAt(i))))
                prefixCount++;
            else {
                lastIndex = i;
                break;
            }
        }
        return new PrefixResponse(prefixCount, lastIndex);
    }

    protected MatchResponse buildResponse(boolean isSuccess, int lastIndex) {
        return new MatchResponse(isSuccess, lastIndex + getSuffix().length());
    }
}
