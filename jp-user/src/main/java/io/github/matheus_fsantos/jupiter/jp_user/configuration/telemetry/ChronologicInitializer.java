package io.github.matheus_fsantos.jupiter.jp_user.configuration.telemetry;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChronologicInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("{} - onApplicationEvent - message: spring initialized. Injecting Logback Appender...", getClass().getSimpleName());

        try {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

            ChronologicLogbackAppender chronologicAppender = new ChronologicLogbackAppender();
            chronologicAppender.setContext(loggerContext);
            chronologicAppender.setName("CHRONOLOGIC_STREAM_APPENDER");
            chronologicAppender.start();

            Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
            rootLogger.addAppender(chronologicAppender);

            log.info("{} - onApplicationEvent - message: appender successfully attached to Logback Root!", getClass().getSimpleName());
        } catch (Exception e) {
            log.error("{} - onApplicationEvent - message: critical failure registering appender, exception.message: {}", getClass().getSimpleName(), e.getMessage());
        }
    }
}