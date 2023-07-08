package com.grishare.service;

import com.grishare.base.BaseResponse;
import com.grishare.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public BaseResponse<?> getCountry() {
        List<Country> countryList = quoteRepository.findAll();
        return BaseResponse.ok(countryList);
    }

}
