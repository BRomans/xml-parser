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
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Michele Romani, TAI Solutions
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ValidatorTestConf.class})
public class XMLValidatorServiceTest {

    @Autowired
    ValidatorService saxXmlValidatorService;

    @Test
    public void testServiceCreation(){
        assertNotNull(saxXmlValidatorService);
    }

    @Test
    public void testSampleValidationError(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/books.xml"));
        saxXmlValidatorService.generateXMLValidator("xsd-schema/books.xsd");
        Boolean result = saxXmlValidatorService.validateToXSD(xmlSourceFile);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testSampleValidationValid(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/books-valid.xml"));
        saxXmlValidatorService.generateXMLValidator("xsd-schema/books.xsd");
        Boolean result = saxXmlValidatorService.validateToXSD(xmlSourceFile);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testSingleDocumentValidationValid(){
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR01.xml"));
        Boolean result = saxXmlValidatorService.validateToXSD(xmlSourceFile);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testMultipleDocumentsValidationValid(){
        List<Source> sourceFiles = new ArrayList<>();
        Source xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR01.xml"));
        sourceFiles.add(xmlSourceFile);
        xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR02.xml"));
        sourceFiles.add(xmlSourceFile);
        xmlSourceFile = new StreamSource(new File("src/test/resources/xml-samples/IT01234567890_FPR03.xml"));
        sourceFiles.add(xmlSourceFile);

        List<Boolean> results = new ArrayList<>();
        sourceFiles.forEach(source -> results.add(saxXmlValidatorService.validateToXSD(source)));
        results.forEach(result -> assertTrue(result));
    }
}
