package com.luxoft.regexp.engine.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchResponse {

    private Boolean result;
    private Integer index;
}
