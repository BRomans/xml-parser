# Simple XML validator in Java

This simple software implements two methods, SAX and DOM for validating a generic xml document against its xsd schema.
Java Spring is the core library, and Maven is used for dependency management.
This software is not intended as standalone application, but as a module to be integrated in a bigger context.
Anyway it is possible to test its functionality in the test units:
  - DOMXmlValidatorServiceTest tests the xml against the xsd using the DOM analysis method.
  - XmlValidatorServiceTest tests the xml against the xsd using the SAX analysis method. (much faster)
  - ValidationProcessorServiceTest tests the xml against the xsd using both methods togheter (Configurable in application.properties).
 
In application properties there are some configurable entries, such as xsd location (local or remote) and processor configuration (1 = SAX, 2 = DOM, 3 = BOTH).

If you use this software, or part of it, inside any of your projects please remember to credit my Github page: https://github.com/BRomans
