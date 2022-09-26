package com.luxoft.regexp.engine.matcher;

import com.luxoft.regexp.engine.core.AbstractRegexStepMatcher;
import com.luxoft.regexp.engine.core.RegexStep;
import com.luxoft.regexp.engine.response.MatchResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LengthMatcher extends AbstractRegexStepMatcher {

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
        return new LengthMatcher();
    }

    @Override
    public MatchResponse matches(String text) {
        return new MatchResponse(text.isEmpty(), 0);
    }
}
