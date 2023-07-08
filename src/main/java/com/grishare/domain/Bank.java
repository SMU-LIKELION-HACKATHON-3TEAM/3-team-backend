package com.grishare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bank {
    ECA("한국수출입은행"),
    KB("국민은행");
    private final String bankName;

}
