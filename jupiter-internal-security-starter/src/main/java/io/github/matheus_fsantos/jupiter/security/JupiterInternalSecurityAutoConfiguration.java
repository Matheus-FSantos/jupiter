package io.github.matheus_fsantos.jupiter.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "jupiter.security.public-key")
public class JupiterInternalSecurityAutoConfiguration {
    @Value("${jupiter.security.public-key}")
    private String publicKeyStr;

    @Bean
    public InternalSecurityTokenValidator internalSecurityTokenValidator() throws Exception {
        return new InternalSecurityTokenValidator(publicKeyStr);
    }

    @Bean
    public FilterRegistrationBean<JupiterInternalSecurityFilter> internalSecurityFilter(InternalSecurityTokenValidator validator) {
        FilterRegistrationBean<JupiterInternalSecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JupiterInternalSecurityFilter(validator));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
