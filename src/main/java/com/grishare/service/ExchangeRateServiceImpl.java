package com.grishare.service;

import com.grishare.bankapi.EcaApi;
import com.grishare.domain.Bank;
import com.grishare.domain.ExchangeRate;
import com.grishare.domain.Nation;
import com.grishare.dto.ExchangeRateRequestDto;
import com.grishare.dto.ExchangeRateReturnDto;
import com.grishare.repository.ExchangeRateRepository;
import com.grishare.repository.NationRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService{

    @Autowired
    private ExchangeRateRepository exRepository;

    @Autowired
    private EcaApi ecaApi;

    @Autowired
    private NationRepository nationRepository;

    @Override
    public ExchangeRate save(ExchangeRateRequestDto exRequestDto) {
        try{
            return  exRepository
                    .save(
                            exRequestDto.toEntity()
                    );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update() throws IOException {
        JSONArray jsonArray = ecaApi.getApi();
        List<ExchangeRate> exList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String curUnit = jsonObject.getString("cur_unit");
            String deal_bas_r = jsonObject.getString("deal_bas_r");
            ExchangeRate exchangeRate = ExchangeRate.builder()
                    .bank(Bank.ECA)
                    .curUnit(curUnit.substring(0, 2))
                    .exchangeRate(Float.parseFloat(deal_bas_r))
                    .build();
            exList.add(exchangeRate);
        }

        //for문 (데이터 유무 구분)
        for(int i = 0; i < exList.size(); i++){
            List<Nation> nationList = nationRepository.findByIso2(exList.get(i).getCurUnit());
            if(!nationList.isEmpty()){
                List<ExchangeRate> erList = exRepository.findByNationIdAndBank(nationList.get(0).getId(), exList.get(i).getBank());
                if(!erList.isEmpty()){
                    ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(exList.get(i));
                }else {
                    exRepository.save(exList.get(i));
                }
            }
        }
    }

    @Override
    public ExchangeRateReturnDto findById(Long id) {
        try{
            Optional<ExchangeRate> exData = exRepository.findById(id);
            if(exData.isPresent()){
                return  new ExchangeRateReturnDto(exData.get());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExchangeRateReturnDto findByNationIdAndBank(Long nationId, String bank) {
        List<ExchangeRate> ex = exRepository.findByNationIdAndBank(nationId, Bank.valueOf(bank));
        if(!ex.isEmpty()){
            return new ExchangeRateReturnDto(ex.get(0));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try{
            exRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Scheduled(cron = "* * * * * *", zone = "Asia/Seoul")
//    public void updateExchangeRate() throws IOException {
//        update();
//        System.out.println("gg");
//    }
}
