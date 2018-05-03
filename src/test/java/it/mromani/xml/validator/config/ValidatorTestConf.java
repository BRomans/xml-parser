package it.mromani.xml.validator.config;

import it.mromani.xml.validator.service.ValidationProcessorService;
import it.mromani.xml.validator.service.ValidatorService;
import it.mromani.xml.validator.service.impl.DOMXmlValidatorService;
import it.mromani.xml.validator.service.impl.XMLValidationProcessorService;
import it.mromani.xml.validator.service.impl.XMLValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;
import java.io.IOException;

/**
 *
 * @author Michele Romani, TAI Solutions
 */


@Configuration
@ComponentScan(basePackages={"it.mromani.xml.validator"})
@EnableConfigurationProperties(TestConfig.class)
public class ValidatorTestConf {

    @Value("${validator.service.fatt.xsd}")
    String schemaFilePath;

    @Value("${validator.service.property.external.schema.nonamespace}")
    String schemaLocation;

    @Value("${validator.service.feature.validation}")
    String validationFeature;

    @Value("${validator.service.feature.schema}")
    String schemaFeature;

    @Value("${validator.service.feature.dynamic}")
    String dynamicFeature;

    @Value("${validator.processor.default.configuration}")
    int validatorDefaultConfiguration;

    @Bean
    public ValidatorService saxXmlValidatorService() throws IOException, SAXException {
        return new XMLValidatorService(schemaFilePath);
    }

    @Bean
    public ValidatorService domXmlValidatorService() throws IOException, SAXException {
        return new DOMXmlValidatorService(schemaLocation, schemaFilePath, validationFeature, schemaFeature, dynamicFeature);
    }

    @Bean(name = "validationProcessor")
    @Autowired
    public ValidationProcessorService validationProcessorService(ValidatorService saxXmlValidatorService, ValidatorService domXmlValidatorService){
        return new XMLValidationProcessorService(saxXmlValidatorService, domXmlValidatorService, validatorDefaultConfiguration);
    }
}
