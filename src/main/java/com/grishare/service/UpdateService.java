package com.grishare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UpdateService {

    @Autowired
    ExchangeRateServiceImpl exService;

//    @Scheduled(cron = "0 1 9-22 * * 1-5", zone = "Asia/Seoul")
    @Scheduled(cron = "0 * * * * 1-5", zone = "Asia/Seoul")
    public void updateExchangeRate() throws IOException {
        exService.update();
    }

}
