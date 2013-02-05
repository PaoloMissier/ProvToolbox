package org.openprovenance.prov.neo4j;
import junit.framework.TestCase;

/* This class extend test and use class Neo4jUtility. 
  By changing the path of test file, user can test different PROV document. */
public class Neo4jTest extends TestCase
{
    public void testNeo4jUpload() throws  java.lang.Throwable {

    	Neo4jUtility.provn2neo4j("../prov-gen/src/test/resources/prov/gen2-exp.provn",null);
	
    }
}



                  
