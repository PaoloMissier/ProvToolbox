package org.openprovenance.prov.dot;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.openprovenance.prov.xml.Document;
import org.openprovenance.prov.xml.ProvFactory;
import org.openprovenance.prov.xml.ProvSerialiser;

import javax.xml.bind.JAXBException;
import java.io.File;

import org.antlr.runtime.tree.CommonTree;
import org.openprovenance.prov.notation.Utility;

public class IncidentRoomRenderer extends TestCase {

    public void asnToDot(String asnFile, String xmlFile, String dotFile, String pdfFile)
        throws java.io.FileNotFoundException,  java.io.IOException, JAXBException, Throwable {
        Utility u=new Utility();

        CommonTree tree = u.convertASNToTree(asnFile);

        Document o= (Document) u.convertTreeToJavaBean(tree);

        ProvSerialiser serial=ProvSerialiser.getThreadProvSerialiser();
        
        serial.serialiseDocument(new File(xmlFile),o,true);

        ProvToDot toDot=new ProvToDot("src/main/resources/defaultConfigWithRoleNoLabel.xml"); 
        
        toDot.convert(o,dotFile,pdfFile);
        
    }

    public void testAsnToDot1() throws java.io.FileNotFoundException,  java.io.IOException, JAXBException, Throwable {
        asnToDot("../VDM/src/test/resources/prov/IncidentRoomExample.prov",
                 "../VDM/target/IncidentRoomExample.prov-xml",
                 "../VDM/target/IncidentRoomExample.dot",
                 "../VDM/target/IncidentRoomExample.pdf");
    }
}
