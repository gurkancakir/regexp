package com.luxoft.regexp.controller;

import com.luxoft.regexp.dto.RegexRequest;
import com.luxoft.regexp.engine.Matcher;
import com.luxoft.regexp.engine.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/regex")
@AllArgsConstructor
public class RegexController {

    private final Pattern pattern;

    @PostMapping
    public Mono<Boolean> check(@RequestBody RegexRequest regexRequest) {
        Matcher matcher = pattern.compile(regexRequest.getRegex());
        return Mono.just(matcher.matches(regexRequest.getText()));
    }
}
