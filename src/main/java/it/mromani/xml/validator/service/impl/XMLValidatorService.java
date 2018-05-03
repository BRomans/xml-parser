package it.mromani.xml.validator.service.impl;

import it.mromani.xml.validator.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Michele Romani, TAI Solutions
 */

public class XMLValidatorService implements ValidatorService {

    private SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    private static final Logger log = LoggerFactory.getLogger(XMLValidatorService.class);

    private Schema schema;
    private Validator validator;

    public XMLValidatorService(String schemaFilePath) throws SAXException, IOException {
        ClassPathResource classPathResource = new ClassPathResource(schemaFilePath);
        this.schema = schemaFactory.newSchema(classPathResource.getFile());
        this.validator = schema.newValidator();
    }

    @Override
    public boolean validateToXSD(String filePath) {
        Source xmlSourceFile = new StreamSource(new File(filePath));
        return validateToXSD(filePath);
    }

    @Override
    public boolean validateToXSD(Source xmlFile) {
        Long startTime = System.currentTimeMillis();
        try {
            this.validator.validate(xmlFile);
            log.info("Validation successful");
            Long endTime = System.currentTimeMillis();
            Long executionTime = endTime - startTime;
            log.info("Execution time {} ms", executionTime);
            return true;
        } catch (SAXException sasException) {
            log.error("Validation error");
            sasException.printStackTrace();
            Long endTime = System.currentTimeMillis();
            Long executionTime = endTime - startTime;
            log.info("Execution time {} ms", executionTime);
            return false;
        }catch (IOException ioException){
            log.error("IO error");
            ioException.printStackTrace();
            return false;
        }

    }

    @Override
    public void generateXMLValidator(String schemaFilePath){
        try {
            ClassPathResource classPathResource = new ClassPathResource(schemaFilePath);
            this.schema = schemaFactory.newSchema(classPathResource.getFile());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.validator = this.schema.newValidator();
    }
}
