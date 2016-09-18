package com.shorterurl.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.shorterurl.domain.RegisterUrl;
import com.shorterurl.domain.RegisterUrlRepository;
import com.shorterurl.service.RegisterService;

/**
 * Initialization and registration short url (shorturl) for some sites www.ya.ru.
 *
 * @author Sergey Stotskiy
 *
 */
@Component("registerurl")
@DependsOn({ "applicationProperties" })
public class RegisterUrlInit extends AbstractInit {

    @Autowired
    RegisterUrlRepository<RegisterUrl> registerRepository;

    @Autowired
    RegisterService registerService;

    @Override
    protected void init() {
        create("www.ya.ru", "shorturl_1", 301);
        create("www.index.ru", "shorturl_2", 301);
        create("www.mail.ru", "shorturl_3", 302);
    }

    /**
     * Create one RegisterUrl
     *
     * @param fullUrl
     * @param shortUrl
     * @param redirectType
     */
    private void create(String fullUrl, String shortUrl, int redirectType) {
        RegisterUrl registeredUrl = registerService.registerUrl(fullUrl, redirectType);
        registeredUrl.setShortUrl(shortUrl);
        registerRepository.save(registeredUrl); // update short url link
    }
}