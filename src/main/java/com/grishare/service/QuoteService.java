package com.grishare.service;

import com.grishare.base.BaseResponse;

public interface QuoteService {

    public BaseResponse<?> getCountry();

    public BaseResponse<?> getDivision(Long nationId);
}
