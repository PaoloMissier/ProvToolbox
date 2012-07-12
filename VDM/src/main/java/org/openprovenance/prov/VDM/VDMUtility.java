package org.openprovenance.prov.VDM;
import  org.openprovenance.prov.notation.Utility;
import  org.openprovenance.prov.notation.TreeTraversal;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Hashtable;

import  org.antlr.runtime.CommonTokenStream;
import  org.antlr.runtime.ANTLRFileStream;
import  org.antlr.runtime.CharStream;
import  org.antlr.runtime.Token;
import  org.antlr.runtime.tree.Tree;
import  org.antlr.runtime.tree.CommonTree;
import  org.antlr.runtime.tree.CommonTreeAdaptor;
import  org.antlr.runtime.tree.TreeAdaptor;

import org.openprovenance.prov.xml.ProvFactory;
import org.openprovenance.prov.xml.ProvSerialiser;
import org.openprovenance.prov.xml.BeanTraversal;


public  class VDMUtility extends Utility {

	static final String OUT_DEFAULT = "out.edb";
	
    public String convertTreeToVDM(CommonTree tree) {
        Object o=new TreeTraversal(new VDMConstructor()).convert(tree);
        return (String)o;
    }


    static public String asn2VDM(String file) throws java.io.IOException, javax.xml.bind.JAXBException, Throwable {

	VDMUtility u=new VDMUtility();
        CommonTree tree = u.convertASNToTree(file);

        String s=u.convertTreeToVDM(tree);

	return s;

    }

    
    /* 
     * expose util as command line tool
     */
    static public void main(String[] args)  {

//    	System.out.println(args.length);
    	
    	if (args.length < 1) {
    		usage();
    		return;
    	}
    	
    	String in = args[0], out = null;
   
//    	System.out.println(args[0]);
    	
    	if (args.length > 1) { 
    		System.out.println(args[1]);
    		out = args[1];     		
    	}
    	else out = OUT_DEFAULT;
    
    	try {
    		String outStr = asn2VDM(in);
    		
    		java.io.FileWriter fw = new java.io.FileWriter(out);
    		fw.write(outStr);
    		fw.close();
    		
    		System.out.println("file written to "+out);
    		
		} catch (Throwable e) {
    		System.out.println("Exception: "+e.getMessage());
    		e.printStackTrace();
		}
    	
    }

	static void usage() {
		System.out.println("Usage: asn2VDM <input prov-n> [<output some VDM file...>]");
	}
}





