package com.shorterurl.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorterurl.service.StatisticService;

/**
 * Controller allow to get statistic redirect information.
 *
 * @author Sergey Stotskiy
 *
 */
@RestController
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value = "/statistic/{AccountId}", method = GET)
    public Object statistic(@PathVariable("AccountId") String accountId) {

        if (StringUtils.isEmpty(accountId)) {
            return "AccountId is Null";
        }

        return statisticService.findByLogonName(accountId);
    }

}
