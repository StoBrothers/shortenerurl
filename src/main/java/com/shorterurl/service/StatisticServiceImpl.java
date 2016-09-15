package com.shorterurl.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shorterurl.domain.Account;
import com.shorterurl.domain.RegisterUrl;
import com.shorterurl.domain.Statistic;
import com.shorterurl.domain.StatisticRepository;

@Service("statisticservice")
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StatisticRepository<Statistic> statisticRepository;

    @Autowired
    RegisterService registerservice;

    @Override
    public List<String> findByLogonName(String logonName) {

        List<Statistic> statistics = statisticRepository.findByLogonName(logonName);
        return statistics.stream().map(p -> p.toString()).collect(Collectors.toList());
    }

    @Override
    public List<String> findByAccountId(Long accountId) {
        List<Statistic> statistics = statisticRepository.findByAccountId(accountId);
        return statistics.stream().map(p -> p.toString()).collect(Collectors.toList());
    }

    @Override
    public Optional<Statistic> findByAccountIdAndRegisterUrlId(Long accountId,
        Long registerUrlId) {
        return statisticRepository
            .findByAccountIdAndRegisterUrlId(accountId, registerUrlId).stream()
            .findFirst();
    }

    @Override
    public void updateStatistic(RegisterUrl register) {

        SecurityContext context = SecurityContextHolder.getContext();

        // Get current account
        Account account = ((CurrentAccount) context.getAuthentication().getPrincipal())
            .getAccount();

        Optional<Statistic> wrapperSatistic = findByAccountIdAndRegisterUrlId(
            account.getId(), register.getId());

        Statistic statistic = null;
        if (!wrapperSatistic.isPresent()) {// if statistic is not created earlier
            statistic = new Statistic(account, register);
        } else {
            statistic = wrapperSatistic.get();
        }

        statistic.incCountRedirects();// increment count of redirects
        statisticRepository.save(statistic);
    };

}
