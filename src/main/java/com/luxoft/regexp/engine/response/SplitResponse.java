package com.luxoft.regexp.engine.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitResponse {
    private String remaining;
    private String splitted;
}
