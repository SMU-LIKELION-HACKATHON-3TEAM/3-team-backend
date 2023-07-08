package com.grishare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UpdateService {

    @Autowired
    ExchangeRateServiceImpl exService;

    //    @Scheduled(cron = "0 0 12 * * 1-5", zone = "Asia/Seoul")
//    @Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
//    public void updateExchangeRate() throws IOException {
//        exService.update();
//        System.out.println("gg");
//    }

}
