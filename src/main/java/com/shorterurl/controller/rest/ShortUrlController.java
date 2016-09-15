package com.shorterurl.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorterurl.domain.RegisterUrl;
import com.shorterurl.domain.Statistic;
import com.shorterurl.domain.StatisticRepository;
import com.shorterurl.service.RegisterService;
import com.shorterurl.service.StatisticService;

/**
 * Controller for redirect to full url link over short link (generated url).
 *
 * @author Sergey Stotskiy
 *
 */

@RestController
@RequestMapping("/shorturl")
public class ShortUrlController {

    private static final Logger logger = LoggerFactory
        .getLogger(ShortUrlController.class);

    @Autowired
    RegisterService registerservice;

    @Autowired
    StatisticService statisticService;

    @Autowired
    StatisticRepository<Statistic> statisticRepository;

    @RequestMapping(value = "/{shortUrl}", method = GET)
    @Transactional
    public ResponseEntity<Object> redirectToFullUrl(
        @PathVariable("shortUrl") String shortUrl) {

        Optional<RegisterUrl> registered = registerservice.findByShortlUrl(shortUrl);
        if (!registered.isPresent()) {
            logger.error("Url for this short link is not existed.");
            return null;
        }

        RegisterUrl register = registered.get();

        statisticService.updateStatistic(register); // update statistic for this url

        // make a redirect to full url link
        URI location = URI.create(register.getFullUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(location);

        return new ResponseEntity<>(httpHeaders,
            HttpStatus.valueOf(register.getRedirectType()));
    }
}
