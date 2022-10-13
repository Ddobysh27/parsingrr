package com.trustpilot.parsingrr.handlers;

import com.trustpilot.parsingrr.model.Statistics;
import com.trustpilot.parsingrr.service.RatingAndReviewsParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class StatisticsHandler {

    @Autowired
    RatingAndReviewsParserService ratingAndReviewsParserService;

    public ResponseEntity<Mono<Statistics>> getStatistics(String domain) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ratingAndReviewsParserService.getStatistics(domain));
    }
}

