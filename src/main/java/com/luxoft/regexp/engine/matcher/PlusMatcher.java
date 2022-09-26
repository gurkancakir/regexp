package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.core.AbstractRegexStepMatcher;
import com.luxoft.regexp.engine.response.MatchResponse;
import com.luxoft.regexp.engine.core.RegexStep;
import com.luxoft.regexp.engine.response.PrefixResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PlusMatcher extends AbstractRegexStepMatcher {

    private String pattern;
    private String prefix;
    private String suffix;

    private static final char IND = '+';

    @Override
    public boolean isAcceptable(char ch) {
        return IND == ch;
    }

    @Override
    public RegexStep create(String pattern) {
        PlusMatcher step = new PlusMatcher();
        step.setPattern(pattern);
        int index = pattern.indexOf(IND);
        step.setPrefix(pattern.substring(0, index));
        step.setSuffix(pattern.substring(index + 1));
        return step;
    }

    @Override
    public MatchResponse matches(String text) {
        PrefixResponse prefixResponse = searchPrefix(text, getPrefix());
        boolean result =  checkPrefix(prefixResponse.getPrefixCount()) && checkSuffix(text, prefixResponse.getLastIndex());
        return buildResponse(result, prefixResponse.getLastIndex());
    }

    private boolean checkPrefix(int prefixCount) {
        return prefixCount >= 1;
    }
}
