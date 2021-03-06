package org.openprovenance.prov.datalog;
import org.antlr.runtime.tree.CommonTree;
import org.openprovenance.prov.notation.TreeTraversal;
import org.openprovenance.prov.notation.Utility;


public  class DataLogUtility extends Utility {

	static final String OUT_DEFAULT = "out.edb";
	
    public String convertTreeToDatalog(CommonTree tree) {
        Object o=new TreeTraversal(new DataLogConstructor()).convert(tree);
        return (String)o;
    }


    static public String asn2datalog(String file) throws java.io.IOException, javax.xml.bind.JAXBException, Throwable {

	DataLogUtility u=new DataLogUtility();
        CommonTree tree = u.convertASNToTree(file);

        String s=u.convertTreeToDatalog(tree);

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
    		String outStr = asn2datalog(in);
    		
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
		System.out.println("Usage: asn2datalog <input prov-n> [<output datalog edb>]");
	}
}





