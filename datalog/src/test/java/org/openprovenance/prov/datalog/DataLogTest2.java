package org.openprovenance.prov.datalog;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DataLogTest2 extends TestCase
{
	
    public void testDataLog() throws  java.lang.Throwable {

//	DataLogUtility u=new DataLogUtility();

	String s=DataLogUtility.asn2datalog("src/test/resources/prov/prov-datalog-converter-test-incremental.pn",null);

	System.out.println("=============");
	System.out.println(s);
	System.out.println("=============");

	assertTrue(s!= null);
    }
}


