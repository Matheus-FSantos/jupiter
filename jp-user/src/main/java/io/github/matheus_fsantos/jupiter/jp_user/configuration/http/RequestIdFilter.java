package io.github.matheus_fsantos.jupiter.jp_user.configuration.http;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class RequestIdFilter implements Filter {
    private static final String TRACE_ID_KEY = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String traceId = httpRequest.getHeader("X-Trace-Id");

            if (traceId == null || traceId.isBlank()) {
                traceId = UUID.randomUUID().toString();
            }

            MDC.put(TRACE_ID_KEY, traceId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID_KEY);
        }
    }
}
