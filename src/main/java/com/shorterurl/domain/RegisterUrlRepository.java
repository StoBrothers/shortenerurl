package com.shorterurl.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RegisterUrl repository.
 *
 * @author Sergey Stotskiy
 *
 * @param <T>
 */
public interface RegisterUrlRepository<T extends RegisterUrl>
    extends JpaRepository<T, Long> {

    Optional<RegisterUrl> findOneByFullUrl(String fullUrl);

    Optional<RegisterUrl> findOneByShortUrl(String shortUrl);

}
