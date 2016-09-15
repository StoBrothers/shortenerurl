package com.shorterurl.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatisticRepository<T extends Statistic> extends JpaRepository<T, Long> {

    @Query("select  t from #{#entityName} t " + " where t.account.id  = :accountId "
        + " and t.registerUrl.id = :registerUrlId)")
    List<Statistic> findByAccountIdAndRegisterUrlId(
        @Param(value = "accountId") Long accountId,
        @Param(value = "registerUrlId") Long registerUrlId);

    @Query("select  t from #{#entityName} t " + " where t.account.id  = :accountId ")
    List<Statistic> findByAccountId(@Param(value = "accountId") Long accountId);

    @Query("select  t from #{#entityName} t "
        + " where t.account.logonName  = :accountLogonName ")
    List<Statistic> findByLogonName(@Param(value = "accountLogonName") String logonName);

}
