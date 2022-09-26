package com.luxoft.regexp.dto;

import lombok.Data;

@Data
public class RegexRequest {
    private String regex;
    private String text;
}
