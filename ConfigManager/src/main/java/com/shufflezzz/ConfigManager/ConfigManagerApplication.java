package com.shufflezzz.ConfigManager;

import com.shufflezzz.Connector.config.SharedConfigReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SharedConfigReference.class)
public class ConfigManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigManagerApplication.class, args);
    }

}
