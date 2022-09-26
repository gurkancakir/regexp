package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.core.AbstractRegexStepMatcher;
import com.luxoft.regexp.engine.core.RegexStep;
import com.luxoft.regexp.engine.response.MatchResponse;
import com.luxoft.regexp.engine.response.SplitResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DotMatcher extends AbstractRegexStepMatcher {

    private String pattern;
    private String prefix;
    private String suffix;

    private static final char DOT = '.';

    @Override
    public boolean isAcceptable(char ch) {
        return DOT == ch;
    }

    @Override
    public RegexStep create(String pattern) {
        DotMatcher dotMatcher = new DotMatcher();
        dotMatcher.setPattern(pattern);
        dotMatcher.setPrefix("");
        dotMatcher.setSuffix(pattern.substring(1));
        return dotMatcher;
    }

    @Override
    public MatchResponse matches(String text) {
        int lastIndex = 1;
        boolean isSuccess = text.length() > 0 && checkSuffix(text, lastIndex);
        return buildResponse(isSuccess, lastIndex);
    }

    @Override
    public SplitResponse split(String pattern, int index) {
        SplitResponse splitResponse = new SplitResponse();
        splitResponse.setSplitted(pattern.substring(index));
        splitResponse.setRemaining(pattern.substring(0, index));
        return splitResponse;
    }
}
