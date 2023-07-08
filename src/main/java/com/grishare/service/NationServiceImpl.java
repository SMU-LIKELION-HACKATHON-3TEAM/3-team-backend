package com.grishare.service;

import com.grishare.domain.Nation;
import com.grishare.dto.NationRequestDto;
import com.grishare.dto.NationReturnDto;
import com.grishare.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NationServiceImpl implements NationService{

    @Autowired
    private NationRepository nationRepository;

    @Override
    public Nation save(NationRequestDto nationRequestDto) {
        try{
            return  nationRepository
                    .save(
                            nationRequestDto.toEntity()
                    );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NationReturnDto update(Long id, NationRequestDto nationRequestDto) {
        try{
            Optional<Nation> nationData = nationRepository.findById(id);
            if(nationData.isPresent()){
                Nation _nation = nationData.get();
                _nation.setIso2(nationRequestDto.getISO_3166_1_alpha_2());
                _nation.setIso3(nationRequestDto.getISO_3166_1_alpha_3());
                _nation.setIsoN(nationRequestDto.getISO_3166_1_numeric());
                _nation.setCountryName(nationRequestDto.getCountryName());
                _nation.setCountryEnName(nationRequestDto.getCountryEnName());
                _nation.setContinentCode(nationRequestDto.getContinentCode());
                _nation.setTravelWarning(nationRequestDto.getTravelWarning());
                _nation.setWarningHistory(nationRequestDto.getWarningHistory());
                return  new NationReturnDto(_nation);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NationReturnDto findById(Long id) {
        try{
            Optional<Nation> nationData = nationRepository.findById(id);
            if(nationData.isPresent()){
                return  new NationReturnDto(nationData.get());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NationReturnDto> findAll() {
        List<Nation> nationList = nationRepository.findAll();
        List<NationReturnDto> nationReturnDtoList = new ArrayList<>();
        return nationList.stream().map(NationReturnDto::new).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        try{
            nationRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
