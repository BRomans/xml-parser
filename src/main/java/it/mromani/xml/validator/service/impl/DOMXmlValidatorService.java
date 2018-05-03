package it.mromani.xml.validator.service.impl;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import it.mromani.xml.validator.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

/**
 *
 * @author Michele Romani, TAI Solutions
 */

public class DOMXmlValidatorService implements ValidatorService {

    private static DOMParser parser = new DOMParser();
    private static final Logger log = LoggerFactory.getLogger(DOMXmlValidatorService.class);
    private String schemaLocation;
    private String schemaFileUrl;
    private Document document;

    public DOMXmlValidatorService(String schemaLocation, String schemaFileUrl, String validationFeature, String schemaFeature, String dynamicFeature){
        this.schemaLocation = schemaLocation;
        this.schemaFileUrl = schemaFileUrl;
        try {
            parser.setProperty(schemaLocation, schemaFileUrl);
            SAXParserErrorHandler errorHandler = new SAXParserErrorHandler();
            parser.setErrorHandler(errorHandler);
        } catch (SAXNotRecognizedException e) {
            log.error("Could not load schema {} ", schemaFileUrl);
            e.printStackTrace();
        } catch (SAXNotSupportedException e) {
            e.printStackTrace();
        }
        setFeature(validationFeature, true);
        setFeature(schemaFeature,true);
        setFeature(dynamicFeature,true);
    }


    @Override
    public boolean validateToXSD(Source fileSource) {
        StreamSource streamFileSource = (StreamSource)fileSource;
        String filePath = streamFileSource.getSystemId();
        return validateToXSD(filePath);
    }

    @Override
    public boolean validateToXSD(String filePath) {
        Long startTime = System.currentTimeMillis();
        try {
            parser.parse(filePath);
            document = parser.getDocument();
            Long endTime = System.currentTimeMillis();
            Long executionTime = endTime - startTime;
            log.info("Execution time {} ms", executionTime);
            log.info("Validation successful of Document {}", document.getImplementation());
            return true;
        } catch (SAXException e) {
            Long endTime = System.currentTimeMillis();
            Long executionTime = endTime - startTime;
            log.info("Execution time {} ms", executionTime);
            log.info("Could not parse Document {} ", filePath);
            log.error("Stacktrace {} ", e);
            return false;
        }catch (IOException ie){
            log.info("Could not read file {} ", filePath);
            log.error("Stacktrace {} ", ie);
            return false;
        }
    }

    @Override
    public void generateXMLValidator(String schemaFileUrl) {
        try {
            parser.setProperty(schemaLocation, schemaFileUrl);
        } catch (SAXNotRecognizedException e) {
            log.info("Unrecognized property {} ", schemaLocation);
            log.error("Stacktrace {} ", e);
        } catch (SAXNotSupportedException e) {
            log.info("Unrecognized property {} ", schemaLocation);
            log.error("Stacktrace {} ", e);
        }
    }

    private static void setFeature(String feature, boolean setting){
        try {
            parser.setFeature(feature, setting);
        } catch (SAXNotRecognizedException e) {
            log.info("Unrecognized feature {} ", feature);
            log.error("Stacktrace", e);
        } catch (SAXNotSupportedException e) {
            log.info("Unrecognized feature {} ", feature);
            log.error("Stacktrace", e);
        }
    }

}
