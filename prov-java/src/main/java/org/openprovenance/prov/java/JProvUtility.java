package org.openprovenance.prov.java;

import java.util.Map;

import org.antlr.runtime.tree.CommonTree;
import org.openprovenance.prov.java.component4.Bundle;
import org.openprovenance.prov.notation.ASNConstructor;
import org.openprovenance.prov.notation.TreeTraversal;
import org.openprovenance.prov.notation.Utility;

public class JProvUtility extends Utility {

	public NSBundle convertASNToJava(String file) throws java.io.IOException, Throwable { 
		CommonTree tree = convertASNToTree(file);
		NSBundle b = convertTreeToJava(tree);
		return b;
	}
	
	public NSBundle convertTreeToJava(CommonTree tree) {
		Object o = new NSTreeTraversal(new JavaConstructor()).convert(tree);
		return (NSBundle) o;
	}
	
	public String convertJavaToASN(Bundle b, 	Map<Object, Object> namespaces) {
		JProvTraversal jt = new JProvTraversal(new JProvTreeConstructor(new ASNConstructor()));
		
		System.out.println("traversal created for convertJavaToASN");
		
        Object o = jt.convert(b,namespaces);

        System.out.println("conversion accomplished");
        
        return (String) o;
    }
	
	public Bundle convertJavaToJava(Bundle b) {
		JProvTraversal jt = new JProvTraversal(new JProvTreeConstructor(new JavaConstructor()));
		Object o = jt.convert(b);
		return (Bundle) o;
	}
}
