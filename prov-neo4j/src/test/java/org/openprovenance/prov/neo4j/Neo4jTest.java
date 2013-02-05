package org.openprovenance.prov.neo4j;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/* This class extend test and use class Neo4jUtility. 
  By changing the path of test file, user can test different PROV document. */
public class Neo4jTest extends TestCase
{
    public void testNeo4j() throws  java.lang.Throwable {

    	Neo4jUtility u = new Neo4jUtility();

    	u.asn2neo4j("../asn/src/test/resources/prov/prov-family.pn",null);
	
    }
}



                  
