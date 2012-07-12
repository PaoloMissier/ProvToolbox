package org.openprovenance.prov.VDM;
import junit.framework.Test;
import junit.framework.TestCase;

public class VDMTest1 extends TestCase
{
    public void testVDM() throws  java.lang.Throwable {

//	DataLogUtility u=new DataLogUtility();

	String s=VDMUtility.asn2VDM("src/test/resources/prov/provExample.prov");

	System.out.println("=============");
	System.out.println(s);
	System.out.println("=============");

    }
}


