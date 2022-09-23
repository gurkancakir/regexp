package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.MatchResponse;
import com.luxoft.regexp.engine.Step;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class GroupMatcher implements Step {

    private String pattern;
    private List<Character> prefix;
    private String suffix;

    private static final char IND_START = '[';
    private static final char IND_END = ']';

    @Override
    public boolean isAcceptable(char ch) {
        return IND_START == ch;
    }

    @Override
    public Step create(String pattern) {
        GroupMatcher step = new GroupMatcher();
        step.setPattern(pattern.substring(1));
        int index = pattern.indexOf(IND_END);
        List<Character> chars = pattern.substring(2, index)
                .chars()
                .mapToObj(e -> (char) e).toList();
        step.setPrefix(chars);
        step.setSuffix(pattern.substring(index + 1));
        return step;
    }

    @Override
    public MatchResponse matches(String text) {
        char first = text.charAt(1);
        int lastIndex = 2;
        boolean result =  getPrefix().contains(first) && checkSuffix(text, lastIndex);
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
