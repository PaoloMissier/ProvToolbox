package org.openprovenance.prov.datalog;
import junit.framework.Test;
import junit.framework.TestCase;

public class DataLogTest1 extends TestCase
{
    public void testDataLog() throws  java.lang.Throwable {

//	DataLogUtility u=new DataLogUtility();

	String s=DataLogUtility.asn2datalog("../asn/src/test/resources/prov/w3c-publication1.prov-asn");

	System.out.println("=============");
	System.out.println(s);
	System.out.println("=============");

    }
}


