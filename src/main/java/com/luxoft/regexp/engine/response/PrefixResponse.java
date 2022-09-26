package com.luxoft.regexp.engine.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrefixResponse {
    private Integer prefixCount;
    private Integer lastIndex;
}
