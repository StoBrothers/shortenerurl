package com.shorterurl.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.shorterurl.domain.RegisterUrl;
import com.shorterurl.domain.Statistic;

/**
 *
 * Service for work with statistic.
 *
 * @author Sergey Stotskiy
 *
 */
public interface StatisticService {

    /**
     * Get full statistic by accountId
     * 
     * @param accountId
     * @return list with statistic
     */
    List<String> findByAccountId(Long accountId);

    /**
     * Get full statistic by logonName
     * 
     * @param accountId
     * @return Map with statistic
     */
    Map<String, Integer> findByLogonName(String logonName);

    /**
     *
     * @param accountId
     * @param registerUrlId
     * @return
     */
    Optional<Statistic> findByAccountIdAndRegisterUrlId(Long accountId,
        Long registerUrlId);

    /**
     * Update statistic fort this registered url. Count of redirected attempts will be increment.
     * 
     * @param register
     */
    void updateStatistic(RegisterUrl register);

}
