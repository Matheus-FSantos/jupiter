package io.github.matheus_fsantos.jupiter.jp_service_discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class JpServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpServiceDiscoveryApplication.class, args);
	}

}
