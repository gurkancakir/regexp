package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.core.AbstractStepMatcher;
import com.luxoft.regexp.engine.response.MatchResponse;
import com.luxoft.regexp.engine.core.Step;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class GroupMatcher extends AbstractStepMatcher {

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
        step.setPattern(pattern);
        int startIndex = pattern.indexOf(IND_START);
        int endIndex = pattern.indexOf(IND_END);
        List<Character> chars = pattern
                .substring(startIndex + 1, endIndex)
                .chars()
                .mapToObj(e -> (char) e).toList();
        step.setPrefix(chars);
        step.setSuffix(pattern.substring(endIndex + 1));
        return step;
    }

    @Override
    public MatchResponse matches(String text) {
        char ch = text.charAt(1);
        int lastIndex = 2;
        boolean result =  checkPrefix(ch) && checkSuffix(text, lastIndex);
        return buildResponse(result, lastIndex);
    }

    @Override
    public String split(String pattern, int i, int lastIndex) {
        return pattern.substring(i, lastIndex);
    }

    private boolean checkPrefix(char ch) {
        return getPrefix().contains(ch);
    }
}
