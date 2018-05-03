package it.mromani.xml.validator.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Michele Romani, TAI Solutions
 */

public class SAXParserErrorHandler extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(SAXParserErrorHandler.class);


    public SAXParserErrorHandler() {}
    public void warning(SAXParseException ex) throws SAXException {
        log.warn("WARNING {} " , ex.getMessage());
        //throw new SAXException(ex);
    }
    public void error(SAXParseException ex) throws SAXException {
        log.error("ERROR {} " , ex.getMessage());
        throw new SAXException(ex);
    }
    public void fatalError(SAXParseException ex) throws SAXException {
        log.error("FATAL ERROR {} " , ex.getMessage());
        throw new SAXException(ex);
    }
}
