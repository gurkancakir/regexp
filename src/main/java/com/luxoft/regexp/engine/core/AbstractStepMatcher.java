package com.luxoft.regexp.engine.core;

import com.luxoft.regexp.engine.response.MatchResponse;
import com.luxoft.regexp.engine.response.PrefixResponse;

public abstract class AbstractStepMatcher implements Step {

    protected static final char DOT = '.';

    public abstract String getSuffix();

    protected boolean isSuffixIncludeDot() {
        return getSuffix().contains(String.valueOf(DOT));
    }

    protected boolean checkSuffix(String text, String input) {
        for (int i = 0; i < text.length(); i++) {
            if (DOT == text.charAt(i)) ; //nothing
            else if (text.charAt(i) != input.charAt(i))
                return false;
        }
        return true;
    }

    protected boolean checkSuffix(String text, int lastIndex) {
        if (text.substring(lastIndex).length() < getSuffix().length())
            return false;
        String input = text.substring(lastIndex, lastIndex + getSuffix().length());
        if (isSuffixIncludeDot())
            return checkSuffix(getSuffix(), input);
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
