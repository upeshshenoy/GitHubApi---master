package com.telstra.codechallenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.telstra.codechallenge.bean.Quote;

@Service
public class SpringBootQuotesService {

  @Value("${quotes.base.url}")
  private String quotesBaseUrl;

  private RestTemplate restTemplate;

  public SpringBootQuotesService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Returns an array of spring boot quotes. Taken from https://spring.io/guides/gs/consuming-rest/.
   *
   * @return - a quote array
   */
  public Quote[] getQuotes() {

    return restTemplate.getForObject(quotesBaseUrl + "/api", Quote[].class);
  }

  /**
   * Returns a random spring boot quote. Taken from https://spring.io/guides/gs/consuming-rest/.
   *
   * @return - a quote
   */
  public Quote getRandomQuote() {
    return restTemplate.getForObject(quotesBaseUrl + "/api/random", Quote.class);
  }
}
