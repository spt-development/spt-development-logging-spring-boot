package com.spt.development.logging.spring.boot.autoconfigure;

import com.spt.development.logging.spring.DaoSupportLogger;
import com.spt.development.logging.spring.JmsListenerLogger;
import com.spt.development.logging.spring.RepositoryLogger;
import com.spt.development.logging.spring.RestControllerLogger;
import com.spt.development.logging.spring.ServiceLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class LoggingSpringAutoConfigurationTest {
    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void init() {
        this.context = new AnnotationConfigApplicationContext();
    }

    @AfterEach
    void closeContext() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    void register_happyPath_shouldRegisterLoggers() {
        TestPropertyValues.of(
            "spt.logging.dao-support.enabled:true"
        ).applyTo(context);

        context.register(LoggingSpringAutoConfiguration.class);
        context.refresh();

        assertThat(context.getBean(RestControllerLogger.class), is(notNullValue()));
        assertThat(context.getBean(JmsListenerLogger.class), is(notNullValue()));
        assertThat(context.getBean(ServiceLogger.class), is(notNullValue()));
        assertThat(context.getBean(RepositoryLogger.class), is(notNullValue()));
        assertThat(context.getBean(DaoSupportLogger.class), is(notNullValue()));
    }
}