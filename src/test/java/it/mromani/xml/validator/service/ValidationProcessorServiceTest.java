package it.mromani.xml.validator.service;

import it.mromani.xml.validator.config.ValidatorTestConf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Michele Romani, TAI Solutions
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ValidatorTestConf.class})
public class ValidationProcessorServiceTest {

    @Autowired
    ValidationProcessorService validationProcessor;

    @Test
    public void testServiceCreation(){
        assertNotNull(validationProcessor);
    }

    @Test
    public void testDefaultConfigurationSingleDocumentValidationValid(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR01.xml"));
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testDefaultConfigurationSingleDocumentValidationError(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/fattura-errata.xml"));
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testSaxSingleDocumentValidationValid(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR01.xml"));
        validationProcessor.setDefaultConfiguration(1);
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testSaxSingleDocumentValidationError(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/fattura-errata.xml"));
        validationProcessor.setDefaultConfiguration(1);
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testDomSingleDocumentValidationValid(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR01.xml"));
        validationProcessor.setDefaultConfiguration(2);
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testDomSingleDocumentValidationError(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/fattura-errata.xml"));
        validationProcessor.setDefaultConfiguration(2);
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testCompleteSingleDocumentValidationValid(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR01.xml"));
        validationProcessor.setDefaultConfiguration(3);
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testCompleteSingleDocumentValidationError(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/fattura-errata.xml"));
        validationProcessor.setDefaultConfiguration(3);
        Boolean result = validationProcessor.processDocumentValidation(xmlSourceFile);
        assertNotNull(result);
        assertFalse(result);
    }

}
