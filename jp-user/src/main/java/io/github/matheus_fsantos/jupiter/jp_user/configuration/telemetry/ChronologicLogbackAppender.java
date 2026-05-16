package io.github.matheus_fsantos.jupiter.jp_user.configuration.telemetry;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChronologicLogbackAppender extends AppenderBase<ILoggingEvent> {
    private final HttpClient httpClient;

    public ChronologicLogbackAppender() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (event == null || event.getMDCPropertyMap() == null || event.getMDCPropertyMap().get("traceId") == null) {
            return;
        }

        try {
            String traceId = event.getMDCPropertyMap().get("traceId");
            String timestamp = java.time.Instant.ofEpochMilli(event.getTimeStamp()).toString();
            String level = event.getLevel().toString();
            String message = event.getFormattedMessage();
            String threadName = event.getThreadName();
            String loggerName = event.getLoggerName();

            String payload = String.format("""
                [
                  {
                    "timestamp": "%s",
                    "app_name": "jp-user",
                    "environment": "LOCAL",
                    "level": "%s",
                    "trace_id": "%s",
                    "message": "%s",
                    "metadata": "{\\"thread_name\\":\\"%s\\",\\"logger_name\\":\\"%s\\"}"
                  }
                ]
                """,
                    timestamp, level, traceId, message.replace("\"", "\\\""), threadName, loggerName
            );

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/persist/logs"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            addError("Fail to forward log to Chronologic Core: " + e.getMessage());
        }
    }
}
