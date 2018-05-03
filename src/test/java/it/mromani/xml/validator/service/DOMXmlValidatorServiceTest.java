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
public class DOMXmlValidatorServiceTest {

    @Autowired
    ValidatorService domXmlValidatorService;

    @Test
    public void testServiceCreation() {
        assertNotNull(domXmlValidatorService);
    }

    @Test
    public void testSampleValidationError(){
        String filePath = "src/test/resources/xml-samples/books.xml";
        domXmlValidatorService.generateXMLValidator("xsd-schema/books.xsd");
        Boolean result = domXmlValidatorService.validateToXSD(filePath);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testSampleValidationValid(){
        String filePath = "src/test/resources/xml-samples/books-valid.xml";
        domXmlValidatorService.generateXMLValidator("xsd-schema/books.xsd");
        Boolean result = domXmlValidatorService.validateToXSD(filePath);
        assertNotNull(result);
        assertTrue(result);

    }

    @Test
    public void testSingleDocumentValidationValid(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR02.xml"));
        Boolean result = domXmlValidatorService.validateToXSD(xmlSourceFile);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testSingleDocumentValidationError(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/fattura-errata.xml"));
        Boolean result = domXmlValidatorService.validateToXSD(xmlSourceFile);
        assertNotNull(result);
        assertFalse(result);
    }
}