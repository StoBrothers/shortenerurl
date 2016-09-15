package com.shorterurl.controller.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shorterurl.domain.RegisterParameter;
import com.shorterurl.domain.RegisterUrl;
import com.shorterurl.service.RegisterService;

/**
 * New short url registration.
 * 
 * @author Sergey Stotskiy
 *
 */
@RestController
public class RegisterUrlController {

    @Autowired
    RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {
        "application/json" }, produces = { "application/json" })
    @Transactional
    public Object registration(@RequestBody RegisterParameter parameter,
        HttpServletRequest request) {

        RegisterUrl registeredUrl = registerService.registerUrl(parameter.getUrl(),
            parameter.getRedirectType());
        if (registeredUrl == null) {
            return "Errors, please to see log";
        }

        Map<String, String> response = new HashMap<>();
        // request.get

        response.put("shortUrl",
            registerService.getHeaderUrl() + registeredUrl.getShortUrl());

        return response;
    }

}
