package com.grishare.service;

import com.grishare.base.BaseResponse;
import com.grishare.domain.AdministrativeDivision;
import com.grishare.exception.CustomException;
import com.grishare.exception.ErrorCode;
import com.grishare.repository.AdministrativeDivisionRepository;
import com.grishare.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private AdministrativeDivisionRepository administrativeDivisionRepository;

    public BaseResponse<?> getCountry() {
        List<Country> countryList = quoteRepository.findAll();
        return BaseResponse.ok(countryList);
    }

    public BaseResponse<?> getDivision(char iso) {
        try {
            List<AdministrativeDivision> adList = administrativeDivisionRepository.findAllByIso_3166_2(iso);
            if (!adList.isEmpty()) {
                return BaseResponse.ok(adList);
            } else return BaseResponse.fail(ErrorCode.NOT_FOUND);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
    }

}
