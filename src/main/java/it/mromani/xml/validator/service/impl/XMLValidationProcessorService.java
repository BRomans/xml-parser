package it.mromani.xml.validator.service.impl;

import it.mromani.xml.validator.service.ValidationProcessorService;
import it.mromani.xml.validator.service.ValidatorService;
import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.Source;

/**
 *
 * @author Michele Romani, TAI Solutions
 */

@Slf4j
public class XMLValidationProcessorService implements ValidationProcessorService {

    private ValidatorService saxValidator;
    private ValidatorService domValidator;

    private int defaultConfiguration;

    public XMLValidationProcessorService(ValidatorService saxValidator, ValidatorService domValidator){
        this.saxValidator = saxValidator;
        this.domValidator = domValidator;
    }

    public XMLValidationProcessorService(ValidatorService saxValidator, ValidatorService domValidator, int configuration){
        this.saxValidator = saxValidator;
        this.domValidator = domValidator;
        this.defaultConfiguration = configuration;
    }

    @Override
    public boolean processDocumentValidation(Source xmlSourceFile) {
        return processDocumentValidation(defaultConfiguration, xmlSourceFile);
    }

    @Override
    public boolean processDocumentValidation(int configuration, Source xmlSourceFile) {
        switch(configuration){
            case SAX_PARSER_CONFIGURATION:
                return processSaxValidation(xmlSourceFile);
            case DOM_PARSER_CONFIGURATION:
                return processDomValidation(xmlSourceFile);
            case ALL_PARSER_CONFIGURATIONS:
                return processCompleteValidation(xmlSourceFile);
            default:
                return false;
        }
    }

    public void setDefaultConfiguration(int configuration){
        this.defaultConfiguration = configuration;
    }

    public int getDefaultConfiguration(){
        return this.defaultConfiguration;
    }

    private boolean processSaxValidation(Source xmlSourceFile){
        log.info("Processing sax validation of document...");
        return saxValidator.validateToXSD(xmlSourceFile);
    }

    private boolean processDomValidation(Source xmlSourceFile){
        log.info("Processing dom validation of document...");
        return domValidator.validateToXSD(xmlSourceFile);
    }

    private boolean processCompleteValidation(Source xmlSourceFile){
        log.info("Processing complete validation of document...");
        return saxValidator.validateToXSD(xmlSourceFile) && domValidator.validateToXSD(xmlSourceFile);
    }
}
