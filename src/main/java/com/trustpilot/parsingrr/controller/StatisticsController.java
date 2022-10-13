package com.trustpilot.parsingrr.controller;

import com.trustpilot.parsingrr.handlers.StatisticsHandler;
import com.trustpilot.parsingrr.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
public class StatisticsController {

    @Autowired
    StatisticsHandler statisticsHandler;

    @GetMapping("/reviews/{domain}")
    public Mono<ResponseEntity<Mono<Statistics>>> mono(@PathVariable("domain") String domain) {
        return Mono.just(statisticsHandler.getStatistics(domain));
    }

}
