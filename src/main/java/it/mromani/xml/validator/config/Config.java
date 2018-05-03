package it.mromani.xml.validator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Michele Romani, TAI Solutions
 */

@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:application.properties")
public class Config {
}
