package com.shorterurl.config;

import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configure Spring.
 * 
 * @author Sergey Stotskiy
 */
@Configuration
public class ActivitiConfig {

    @Bean
    SpringProcessEngineConfiguration getEngineConfiguration(DataSource dataSource,
        PlatformTransactionManager platformTransactionManager) throws URISyntaxException {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
        springProcessEngineConfiguration.setDataSource(dataSource);
        springProcessEngineConfiguration
            .setTransactionManager(platformTransactionManager);
        springProcessEngineConfiguration
            .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        springProcessEngineConfiguration.setJobExecutorActivate(false);
        return springProcessEngineConfiguration;
    }

    @Bean
    ProcessEngineFactoryBean getProcessEngineFactoryBean(
        SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean
            .setProcessEngineConfiguration(springProcessEngineConfiguration);
        return processEngineFactoryBean;
    }

    @Bean
    RepositoryService getRepositoryService(
        ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
        return processEngineFactoryBean.getObject().getRepositoryService();
    }

    @Bean
    RuntimeService getRuntimeService(ProcessEngineFactoryBean processEngineFactoryBean)
        throws Exception {
        return processEngineFactoryBean.getObject().getRuntimeService();
    }

    @Bean
    TaskService getTaskService(ProcessEngineFactoryBean processEngineFactoryBean)
        throws Exception {
        TaskService taskService = processEngineFactoryBean.getObject().getTaskService();
        return taskService;
    }

    @Bean
    HistoryService getHistoryService(ProcessEngineFactoryBean processEngineFactoryBean)
        throws Exception {
        return processEngineFactoryBean.getObject().getHistoryService();
    }

    @Bean
    ManagementService getManagementService(
        ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
        return processEngineFactoryBean.getObject().getManagementService();
    }

}
