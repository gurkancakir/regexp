package com.luxoft.regexp;

import com.luxoft.regexp.engine.Matcher;
import com.luxoft.regexp.engine.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestCase implements CommandLineRunner {

    private final Pattern pattern;

    @Override
    public void run(String... args) throws Exception {
        Matcher matcher = pattern.compile("a*bcd?xp gu...n[abc]4");
        boolean result = matcher.matches("abcdxp gurkanb4");
        System.out.println("Result : " + result);
    }
}
