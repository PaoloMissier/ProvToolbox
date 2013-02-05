package org.openprovenance.prov.neo4j;
import org.antlr.runtime.tree.CommonTree;
import org.openprovenance.prov.notation.TreeTraversal;
import org.openprovenance.prov.notation.Utility;

/* This class extend utility and use java class Neo4jConstructor. */
public  class Neo4jUtility extends Utility {

    public String convertTreeToNeo4j(CommonTree tree) {
        Object o=new TreeTraversal(new Neo4jConstructor()).convert(tree);
        return (String)o;
    }


	static public String provn2neo4j(String file, String file2) throws java.io.IOException, javax.xml.bind.JAXBException, Throwable {

		Neo4jUtility u=new Neo4jUtility();
        CommonTree tree = u.convertASNToTree(file);

        String s=u.convertTreeToNeo4j(tree);

        return s;

    }


}





