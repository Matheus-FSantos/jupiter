package io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.security;

import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.InternalSecurityTokenOutputPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class InternalSecurityFilter extends OncePerRequestFilter {
    private final InternalSecurityTokenOutputPort internalSecurityTokenOutputPort;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-INTERNAL-TOKEN");

        if(token == null || Boolean.FALSE.equals(internalSecurityTokenOutputPort.isValid(token))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = """
            {
                "timestamp": "%s",
                "status": 401,
                "error": "Unauthorized Access",
                "message": "Access denied; the request must come from the Jupiter cloud gateway.",
                "path": "%s",
                "details": {
                    "reason": "Missing or invalid internal security token",
                    "help": "Ensure the request is routed through the official API Gateway with a valid RS256 signature."
                }
            }
            """.formatted(
                java.time.Instant.now().toString(),
                request.getRequestURI()
            );

            response.getWriter().write(jsonResponse);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
