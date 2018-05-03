package it.mromani.xml.validator.service;

import org.springframework.stereotype.Service;

import javax.xml.transform.Source;

@Service
public interface ValidatorService {


    /**
     * This function tries to validate the xml given on the xsd schema.
     * Depending on the serviced used, it is implemented as Sax parser
     * or Dom parser.
     *
     * @param  fileSource is the SourceStream of the xml file.
     *
     * */
    boolean validateToXSD(Source fileSource);

    /**
     * This function tries to validate the xml given on the xsd schema.
     * Depending on the serviced used, it is implemented as Sax parser
     * or Dom parser.
     *
     * @param  filePath is the relative path of the xml file.
     *
     * */
    boolean validateToXSD(String filePath);

    /**
     * Allows to change at runtime the xsd schema used by validator.
     *
     * @param schemaFilePath is the relative path of the xsd schema.
     * */
    void generateXMLValidator(String schemaFilePath);
}
