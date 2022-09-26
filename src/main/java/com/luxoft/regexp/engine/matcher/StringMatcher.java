package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.core.AbstractRegexStepMatcher;
import com.luxoft.regexp.engine.core.RegexStep;
import com.luxoft.regexp.engine.response.MatchResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StringMatcher extends AbstractRegexStepMatcher {

    private String prefix;

    @Override
    public String getSuffix() {
        return "";
    }

    @Override
    public boolean isAcceptable(char ch) {
        return false;
    }

    @Override
    public RegexStep create(String pattern) {
        StringMatcher characterMatcher = new StringMatcher();
        characterMatcher.setPrefix(pattern);
        return characterMatcher;
    }

    @Override
    public MatchResponse matches(String text) {
        boolean isSuccess = text.startsWith(getPrefix());
        return new MatchResponse(isSuccess, getPrefix().length());
    }
}
