package io.github.matheus_fsantos.jupiter.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;

public class JupiterInternalSecurityFilter extends OncePerRequestFilter {
    private final InternalSecurityTokenValidator validator;

    public JupiterInternalSecurityFilter(InternalSecurityTokenValidator validator) {
        this.validator = validator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-INTERNAL-TOKEN");

        if (token == null || !validator.isValid(token)) {
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
                        "help": "Ensure the request is routed through the official API Gateway."
                    }
                }
                """.formatted(Instant.now().toString(), request.getRequestURI());

            response.getWriter().write(jsonResponse);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
