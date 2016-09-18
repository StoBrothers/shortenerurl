package com.shorterurl.service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shorterurl.domain.RegisterUrl;
import com.shorterurl.domain.RegisterUrlRepository;

@Service("registerservice")
public class RegisterServiceImpl implements RegisterService {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(RegisterServiceImpl.class);

    @Autowired
    private RegisterUrlRepository<RegisterUrl> registerRepository;

    private static final int MAX_COUNT_ATTEMPTS = 10;

    @Override
    public RegisterUrl registerUrl(String fullUrl, Integer redirectType) {

        if (StringUtils.isEmpty(fullUrl)) {
            LOGGER.error("Value of full url has null value");
            return null;
        }

        // check that this full url was registered earlier
        Optional<RegisterUrl> registered = findByFullUrl(fullUrl);

        if (registered.isPresent()) {
            LOGGER.info("This url was registered earlier");
            return registered.get();
        }

        // try to generate new unique short Url with max count of attempts
        String shortUrl = generateUrl();

        if (shortUrl == null) {
            LOGGER.error("Unable to generate unique short url");
            return null;
        }

        // check redirectType value in bound (301|302)
        if ((redirectType != 301) && (redirectType != 302)) {
            redirectType = 302;
        }
        // Save new unique register note into db
        RegisterUrl register = new RegisterUrl(fullUrl, shortUrl, redirectType);
        return registerRepository.save(register);
    }

    /**
     * Generate new short URL and check this url in db. If this short url generated earlier then are
     * generating new shortUrl. MAX_COUNT_ATTEMPTS is maximum value of attempts to generate new url.
     * 
     * @return new generated short url else NULL
     */
    protected String generateUrl() {
        for (int i = 0; i < MAX_COUNT_ATTEMPTS; i++) {
            String generatedShortUrl = RandomStringUtils.randomAlphabetic(6);
            Optional<RegisterUrl> registerUrl = registerRepository
                .findOneByShortUrl(generatedShortUrl);
            if (!registerUrl.isPresent()) {
                return generatedShortUrl;
            }
        }
        return null;
    }

    @Override
    public Optional<RegisterUrl> findByFullUrl(String fullUrl) {
        return registerRepository.findOneByFullUrl(fullUrl);
    }

    @Override
    public Optional<RegisterUrl> findByShortlUrl(String shortUrl) {
        return registerRepository.findOneByShortUrl(shortUrl);
    }

}
