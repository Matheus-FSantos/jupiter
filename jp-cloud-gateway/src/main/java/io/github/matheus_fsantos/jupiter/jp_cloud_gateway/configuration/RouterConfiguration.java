package io.github.matheus_fsantos.jupiter.jp_cloud_gateway.configuration;

import io.github.matheus_fsantos.jupiter.jp_cloud_gateway.application.ports.out.InternalSecurityFilterOutputPort;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RouterConfiguration {
    @Bean
    public RouterFunction<ServerResponse> applicationRouterConfiguration(InternalSecurityFilterOutputPort internalSecurityFilterOutputPort) {
        return GatewayRouterFunctions
            .route("users-route")
                .route(
                    RequestPredicates.path("/api/users/{*segment}"),
                    HandlerFunctions.http()
                )
                .before(BeforeFilterFunctions.setPath("/users/{segment}"))
                    .filter((request, next) -> {
                        ServerRequest modifiedRequest = ServerRequest.from(request)
                            .header("X-INTERNAL-TOKEN", internalSecurityFilterOutputPort.generateToken())
                        .build();

                        return next.handle(modifiedRequest);
                    })
                .filter(LoadBalancerFilterFunctions.lb("jp-user"))
            .build();
    }
}
