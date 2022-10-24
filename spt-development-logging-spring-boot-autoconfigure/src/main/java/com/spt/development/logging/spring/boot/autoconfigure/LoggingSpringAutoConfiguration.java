package com.spt.development.logging.spring.boot.autoconfigure;

import com.spt.development.logging.spring.JmsListenerLogger;
import com.spt.development.logging.spring.RepositoryLogger;
import com.spt.development.logging.spring.RestControllerLogger;
import com.spt.development.logging.spring.ServiceLogger;
import org.springframework.beans.factory.annotation.Value;
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
    private final boolean mdcDisabled;

    /**
     * Creates a new instance of the configuration bean with the spt.cid.mdc.disabled property.
     *
     * @param mdcDisabled a flag to determine whether the correlation ID can be expected to be in the MDC or not. If
     *                    <code>false</code> then the loggers will explicitly include the current correlation ID in
     *                    the log statements added by the aspects.
     */
    public LoggingSpringAutoConfiguration(@Value("${spt.cid.mdc.disabled:false}") final boolean mdcDisabled) {
        this.mdcDisabled = mdcDisabled;
    }

    /**
     * Creates a {@link RestControllerLogger} (aspect) bean. If the <code>spt.cid.mdc.disabled</code> property is set to
     * <code>true</code>, the correlation ID will be included in the generated log statements.
     *
     * @return a new {@link RestControllerLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({ RestController.class })
    public RestControllerLogger restControllerLogger() {
        return new RestControllerLogger(mdcDisabled);
    }

    /**
     * Creates a {@link JmsListenerLogger} (aspect) bean. If the <code>spt.cid.mdc.disabled</code> property is set to
     * <code>true</code>, the correlation ID will be included in the generated log statements.
     *
     * @return a new {@link JmsListenerLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({ JmsListener.class })
    public JmsListenerLogger jmsListenerLogger() {
        return new JmsListenerLogger(mdcDisabled);
    }

    /**
     * Creates a {@link ServiceLogger} (aspect) bean. If the <code>spt.cid.mdc.disabled</code> property is set to
     * <true>true</true>, the correlation ID will be included in the generated log statements.
     *
     * @return a new {@link ServiceLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceLogger serviceLogger() {
        return new ServiceLogger(mdcDisabled);
    }

    /**
     * Creates a vanilla {@link RepositoryLogger} (aspect) bean.  If the <code>spt.cid.mdc.disabled</code> property is
     * set to <code>true</code>, the correlation ID will be included in the generated log statements.
     *
     * @return a new {@link RepositoryLogger} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public RepositoryLogger repositoryLogger() {
        return new RepositoryLogger(mdcDisabled);
    }
}
