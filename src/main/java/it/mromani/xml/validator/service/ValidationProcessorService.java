package it.mromani.xml.validator.service;

import org.springframework.stereotype.Service;

import javax.xml.transform.Source;

/**
 *
 * @author Michele Romani, TAI Solutions
 */

@Service
public interface ValidationProcessorService {

    final static int SAX_PARSER_CONFIGURATION = 1;

    final static int DOM_PARSER_CONFIGURATION = 2;

    final static int ALL_PARSER_CONFIGURATIONS = 3;

    /**
     *  Process Document XML Validation using specified configuration.
     *  1 processes simple sax validation.
     *  2 processes simple dom validation.
     *  3 processes both validations.
     *
     *  @param configuration represents the choosen configuration.
     *  @param xmlSourceFile is the source file to validate.
     *
     * */
    boolean processDocumentValidation(int configuration, Source xmlSourceFile);

    /**
     *  Process Document XML Validation using default configuration from properties file.
     *  @param xmlSourceFile is the source file to validate.
     *
     * */
    boolean processDocumentValidation(Source xmlSourceFile);

    /**
     * Methods to retrieve and change the configuration of a validation processor.
     *
     * @param configuration
     */
    void setDefaultConfiguration(int configuration);

    int getDefaultConfiguration();
}
