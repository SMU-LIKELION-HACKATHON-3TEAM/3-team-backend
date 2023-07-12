package com.grishare.service;

import com.grishare.base.BaseResponse;
import com.grishare.domain.AdministrativeDivision;
import com.grishare.domain.Nation;
import com.grishare.domain.Quote;
import com.grishare.dto.QuoteNationReturnDto;
import com.grishare.exception.CustomException;
import com.grishare.exception.ErrorCode;
import com.grishare.repository.AdministrativeDivisionRepository;
import com.grishare.repository.NationRepository;
import com.grishare.repository.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private NationRepository nationRepository;

    @Autowired
    private AdministrativeDivisionRepository administrativeDivisionRepository;

    public BaseResponse<?> getCountry() {
        List<Nation> nationList = nationRepository.findAll();
        log.info("nation List 불러오기 완료");
        List<QuoteNationReturnDto> quoteNationReturnDtoList = nationList.stream()
                .map(nation -> new QuoteNationReturnDto(nation.getIso2(), nation.getCountryName()))
                .toList();
        log.info("dto 변환 완료");
        return BaseResponse.ok(quoteNationReturnDtoList);
    }

    public BaseResponse<?> getDivision(char iso) {
        try {
            List<AdministrativeDivision> adList = administrativeDivisionRepository.findAllById(iso);
            if (!adList.isEmpty()) {
                return BaseResponse.ok(adList);
            } else return BaseResponse.fail(ErrorCode.NOT_FOUND);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
    }

}
