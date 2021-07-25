package com.shufflezzz.SecurityManager;

import com.shufflezzz.Connector.config.SharedConfigReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SharedConfigReference.class)
public class SecurityManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityManagerApplication.class, args);
	}

}
