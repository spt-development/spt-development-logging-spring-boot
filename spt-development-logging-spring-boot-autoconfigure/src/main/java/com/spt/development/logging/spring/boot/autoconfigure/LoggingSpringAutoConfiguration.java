package com.spt.development.logging.spring.boot.autoconfigure;

import com.spt.development.logging.spring.JmsListenerLogger;
import com.spt.development.logging.spring.RepositoryLogger;
import com.spt.development.logging.spring.RestControllerLogger;
import com.spt.development.logging.spring.ServiceLogger;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link AutoConfiguration Auto-Configuration} for
 * <a href="https://github.com/spt-development/spt-development-logging-spring">spt-development/spt-development-logging-spring</a>.
 */
@AutoConfiguration
public class LoggingSpringAutoConfiguration {

    /**
     * Creates a vanilla {@link RestControllerLogger} (aspect) bean.
     *
     * @return a new {@link RestControllerLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({ RestController.class })
    public RestControllerLogger restControllerLogger() {
        return new RestControllerLogger();
    }

    /**
     * Creates a vanilla {@link JmsListenerLogger} (aspect) bean.
     *
     * @return a new {@link JmsListenerLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({ JmsListener.class })
    public JmsListenerLogger jmsListenerLogger() {
        return new JmsListenerLogger();
    }

    /**
     * Creates a vanilla {@link ServiceLogger} (aspect) bean.
     *
     * @return a new {@link ServiceLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceLogger serviceLogger() {
        return new ServiceLogger();
    }

    /**
     * Creates a vanilla {@link RepositoryLogger} (aspect) bean.
     *
     * @return a new {@link RepositoryLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public RepositoryLogger repositoryLogger() {
        return new RepositoryLogger();
    }
}
