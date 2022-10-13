package com.trustpilot.parsingrr.service;

import com.trustpilot.parsingrr.exception.ApiRequestException;
import com.trustpilot.parsingrr.model.Statistics;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class RatingAndReviewsParserService {

    public static final String URL = "https://www.trustpilot.com/review/";
    public static final String CLASS_REVIEW_COUNT = "typography_body-l__KUYFJ typography_appearance-subtle__8_H2l styles_text__W4hWi";
    public static final String CLASS_RATING = "typography_body-l__KUYFJ typography_appearance-subtle__8_H2l";

    @Cacheable("statistics")
    public Mono<Statistics> getStatistics(final String domain) {
        Statistics stats = new Statistics();
        try {
            Connection.Response response = Jsoup.connect(URL + domain)
                    .timeout(10000)
                    .ignoreHttpErrors(true)
                    .execute();
            int statusCode = response.statusCode();
            if (statusCode == HttpStatus.OK.value()) {
                Document doc = response.parse();
                String rating = doc.getElementsByClass(CLASS_RATING).text();
                String reviewCount = doc.getElementsByClass(CLASS_REVIEW_COUNT).text();
                stats.setReviewsCount(Integer.parseInt(reviewCount.replaceAll("[^0-9]", "")));
                stats.setRating(Double.parseDouble(rating));
            } else {
                throw new ApiRequestException(response.statusMessage(), statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiRequestException(e.getMessage());
        }
        return Mono.just(stats);
    }
}
