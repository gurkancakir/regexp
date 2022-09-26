package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.core.AbstractRegexStepMatcher;
import com.luxoft.regexp.engine.response.MatchResponse;
import com.luxoft.regexp.engine.core.RegexStep;
import com.luxoft.regexp.engine.response.SplitResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class GroupMatcher extends AbstractRegexStepMatcher {

    private String pattern;
    private List<Character> prefix;
    private String suffix = "";

    private static final char IND_START = '[';
    private static final char IND_END = ']';

    @Override
    public boolean isAcceptable(char ch) {
        return IND_START == ch;
    }

    @Override
    public RegexStep create(String pattern) {
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
        char ch = text.charAt(0);
        int lastIndex = 1;
        boolean result =  checkPrefix(ch) && checkSuffix(text, lastIndex);
        return buildResponse(result, lastIndex);
    }


    @Override
    public SplitResponse split(String pattern, int index) {
        SplitResponse splitResponse = new SplitResponse();
        splitResponse.setSplitted(pattern.substring(index));
        splitResponse.setRemaining(pattern.substring(0, index));
        return splitResponse;
    }

    private boolean checkPrefix(char ch) {
        return getPrefix().contains(ch);
    }
}
