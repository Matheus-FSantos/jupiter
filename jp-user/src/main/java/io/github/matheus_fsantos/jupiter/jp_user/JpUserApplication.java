package io.github.matheus_fsantos.jupiter.jp_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class JpUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpUserApplication.class, args);
	}

}
