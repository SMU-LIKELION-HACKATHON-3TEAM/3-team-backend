package com.grishare.service;

import com.grishare.domain.Nation;
import com.grishare.dto.NationRequestDto;
import com.grishare.dto.NationReturnDto;

import java.util.List;

public interface NationService {

    public Nation save(NationRequestDto nationRequestDto);
    public NationReturnDto update(Long id, NationRequestDto nationRequestDto);

    public NationReturnDto findById(Long id);
    public List<NationReturnDto> findAll();

    public void delete(Long id);

}
