package com.shorterurl.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.shorterurl.domain.RegisterUrl;
import com.shorterurl.domain.RegisterUrlRepository;

/**
 * Initialization and registration short url (shorturl) for www.ya.ru.
 *
 * @author Sergey Stotskiy
 *
 */
@Component("restaurants")
@DependsOn({ "applicationProperties" })
public class RegisterUrlInit extends AbstractInit {

    @Autowired
    RegisterUrlRepository<RegisterUrl> registerRepository;

    @Override
    protected void init() {
          create("shorturl");
    }

    /**
     * Create one restaurant
     *
     * @param restaurantName
     */
    private void create(String restaurantName) {
        RegisterUrl restaurant = new RegisterUrl("www.ya.ru", restaurantName, 301);
        registerRepository.save(restaurant);
    }
}