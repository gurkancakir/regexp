package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.MatchResponse;
import com.luxoft.regexp.engine.Step;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PlusMatcher implements Step {

    private String pattern;
    private String prefix;
    private String suffix;

    private static final char IND = '+';

    @Override
    public boolean isAcceptable(char ch) {
        return IND == ch;
    }

    @Override
    public Step create(String pattern) {
        PlusMatcher step = new PlusMatcher();
        step.setPattern(pattern);
        int index = pattern.indexOf(IND);
        step.setPrefix(pattern.substring(0, index));
        step.setSuffix(pattern.substring(index + 1));
        return step;
    }

    @Override
    public MatchResponse matches(String text) {
        int prefixCount = 0;
        int lastIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            if (getPrefix().equals(String.valueOf(text.charAt(i))))
                prefixCount++;
            else {
                lastIndex = i;
                break;
            }
        }
        String temp = text.substring(lastIndex, lastIndex + suffix.length());
        System.out.println("temp " + temp + " count " + prefixCount + " index " + lastIndex + " text " + text);
        boolean result =  prefixCount >= 1 && checkSuffix(text, lastIndex);
        return new MatchResponse(result, lastIndex + suffix.length());
    }

    private boolean checkSuffix(String text, int lastIndex) {
        if (text.substring(lastIndex).length() < suffix.length())
            return false;
        String input = text.substring(lastIndex, lastIndex + suffix.length());
        if (isSuffixIncludeDot())
            return checkSuffix(getSuffix(), input);
        return getSuffix().equals(input);
    }

    private boolean checkSuffix(String text, String input) {
        for (int i = 0; i < text.length(); i++) {
            if ('.' == text.charAt(i))
                continue;
            else if (text.charAt(i) != input.charAt(i))
                return false;
        }
        return true;
    }

    private boolean isSuffixIncludeDot() {
        return getSuffix().contains(".");
    }
}
