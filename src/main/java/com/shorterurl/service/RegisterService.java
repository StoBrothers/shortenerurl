package com.shorterurl.service;

import java.util.Optional;

import com.shorterurl.domain.RegisterUrl;

public interface RegisterService {

    /**
     * Registering process for unique URL.  
     * @param fullUrl  registering url 
     * @param redirectType  redirect type for this url
     * @return
     */
    RegisterUrl registerUrl(String fullUrl, Integer redirectType);
    /**
     * Find RegisterUrl object by shortUrl 
     * @param shortUrl 
     * @return
     */
    Optional<RegisterUrl> findByShortlUrl(String shortUrl);
    /**
     * Find RegisterUrl object by fullUrl
     * @param fullUrl
     * @return
     */
    Optional<RegisterUrl> findByFullUrl(String fullUrl);
}
