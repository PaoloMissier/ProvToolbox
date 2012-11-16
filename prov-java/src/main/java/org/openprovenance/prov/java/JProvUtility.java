package org.openprovenance.prov.java;

import org.antlr.runtime.tree.CommonTree;
import org.openprovenance.prov.java.component4.Bundle;
import org.openprovenance.prov.notation.ASNConstructor;
import org.openprovenance.prov.notation.TreeTraversal;
import org.openprovenance.prov.notation.Utility;

public class JProvUtility extends Utility {

	public Bundle convertASNToJava(String file) throws java.io.IOException, Throwable { 
		CommonTree tree = convertASNToTree(file);
		Bundle b = convertTreeToJava(tree);
		return b;
	}
	
	public Bundle convertTreeToJava(CommonTree tree) {
		Object o = new TreeTraversal(new JavaConstructor()).convert(tree);
		return (Bundle) o;
	}
	
	public String convertJavaToASN(Bundle b) {
		JProvTraversal jt = new JProvTraversal(new JProvTreeConstructor(new ASNConstructor()));
		
		System.out.println("traversal created for convertJavaToASN");
		
        Object o = jt.convert(b);

        System.out.println("conversion accomplished");
        
        return (String) o;
    }
	
	public Bundle convertJavaToJava(Bundle b) {
		JProvTraversal jt = new JProvTraversal(new JProvTreeConstructor(new JavaConstructor()));
		Object o = jt.convert(b);
		return (Bundle) o;
	}
}
