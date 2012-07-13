package org.openprovenance.prov.dot;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.openprovenance.prov.xml.ProvFactory;
import org.openprovenance.prov.xml.ProvSerialiser;
import org.openprovenance.prov.xml.Bundle;

import javax.xml.bind.JAXBException;
import java.io.File;

import org.antlr.runtime.tree.CommonTree;
import org.openprovenance.prov.notation.Utility;

public class Paolo2Test extends TestCase {

    public void asnToDot(String asnFile, String xmlFile, String dotFile, String pdfFile)
        throws java.io.FileNotFoundException,  java.io.IOException, JAXBException, Throwable {
        Utility u=new Utility();

        CommonTree tree = u.convertASNToTree(asnFile);

        Bundle o= (Bundle) u.convertTreeToJavaBean(tree);

        ProvSerialiser serial=ProvSerialiser.getThreadProvSerialiser();

        System.out.println(" " + o);

        //serial.serialiseBundle(new File(xmlFile),o,true);

        ProvToDot toDot=new ProvToDot("src/main/resources/defaultConfigWithRoleNoLabel.xml"); 
        
        toDot.convert(o,dotFile,pdfFile);
    }

    public void testAsnToDot1() throws java.io.FileNotFoundException,  java.io.IOException, JAXBException, Throwable {
        asnToDot("../VDM/src/test/resources/prov/provExample2.prov",
                 "../VDM/target/provExample2.prov-xml",
                 "../VDM/target/provExample2.dot",
                 "../VDM/target/provExample2.pdf");
    }
}
