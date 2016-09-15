package com.shorterurl.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatisticController {

    @RequestMapping(value ="/statistic/{AccountId}", method = GET)
    public Object statistic(@PathVariable("shortUrl") String shortUrl) {
        return null;
    }


}
