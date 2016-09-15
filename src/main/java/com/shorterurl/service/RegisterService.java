package com.shorterurl.service;

import java.util.Optional;

import com.shorterurl.domain.RegisterUrl;

public interface RegisterService {

    RegisterUrl registerUrl (String fullUrl, Integer redirectType);

    Optional<RegisterUrl> findByShortlUrl (String shortUrl);

    Optional<RegisterUrl> findByFullUrl(String fullUrl);

    Optional<RegisterUrl> save(RegisterUrl register);
    
    String getHeaderUrl();
}
