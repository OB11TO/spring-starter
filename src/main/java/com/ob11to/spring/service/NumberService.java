package com.ob11to.spring.service;

import com.ob11to.spring.util.GenerateNumber;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class NumberService {

    public String getRandomNumber() {
        return GenerateNumber.getRandomPlateNumber();
    }

    @Scheduled(fixedRate = 50)
    public String getNextNumber() {
        return GenerateNumber.getNextPlateNumber();
    }
}
