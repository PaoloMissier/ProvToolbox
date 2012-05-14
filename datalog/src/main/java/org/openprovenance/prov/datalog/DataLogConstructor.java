package org.openprovenance.prov.datalog;

import  org.openprovenance.prov.notation.TreeConstructor;
import  org.antlr.runtime.tree.CommonTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/** For testing purpose, conversion back to ASN. */

public class DataLogConstructor implements TreeConstructor {
    int i=0;
    
    public String genId() {
	return "al_" + (i++);
    }
 
    
    public String optionalAttributes(Object o) { return ""; }
    
    /**
     * if attrs is nil, do nothing. Else create one new relation for each attr-value pair indexed by genId = genId()
     * attrList(genID, attrName, attrValue).
     * @param attrs
     * @return
     */
    public String optionalAttributes(Object attrs, String attrId) {
    
    	StringBuffer attrsOut = new StringBuffer();	

    	String s_attrs=(String)attrs;
        if ("".equals(s_attrs)) {
            return ", nil";
        } else {
	    	
	    	java.util.StringTokenizer allAttrs = new java.util.StringTokenizer((String) attrs, ",");
	    	while (allAttrs.hasMoreTokens()) {
	    		java.util.StringTokenizer attrValue = new java.util.StringTokenizer(allAttrs.nextToken(), "=");
	    		if (attrValue.countTokens() != 2) {  // error TBD
	    			continue;
	    		}
	    		String attr = attrValue.nextToken();
	    		String value = attrValue.nextToken();
	    		attrsOut.append("attrList("+attrId+","+attr+","+value+").\n");
	    	}
            return attrsOut.toString();
        }
    }

    
    public String keyEntitySet(Map<String, String> keSetMap, String keSetId) {
        
    	StringBuffer keSetOut = new StringBuffer();	

    	for (Map.Entry<String, String> entry: keSetMap.entrySet()) {
    		
    		keSetOut.append("keSet("+keSetId+","+entry.getKey()+","+entry.getValue()+").\n");
    	}    	
         return keSetOut.toString();
    }

    
    public String keySet(List<String> keySet, String keySetId) {
        
    	StringBuffer keSetOut = new StringBuffer();	
    	
    	for (String entry: keySet) {
    		keSetOut.append("keySet("+keySetId+","+entry+").\n");
    	}    	
         return keSetOut.toString();
    }

    
    String constructRelation(String predName, Object id, List<String> restArgs, Object attrs) {
    	
    	boolean first = true;
    	StringBuffer s = new StringBuffer();
    	if (id == null) {
    		s.append(predName + "(" );
    	}
    	else {
    		s.append(predName + "(" + id);
    		first = false;
    	}
    	
    	if (restArgs != null) {
    		for (String arg : restArgs) {
    			if (!first) s.append(",");
    			s.append(arg);    		
    			first = false;
    		}	
    	}
    	
    	if (attrs.equals(""))
    		 return s.append(").").toString();
    	else {
    		String val = genId();
    		if (! first) s.append(",");
    		s.append(val+").\n");
    		String convertedAttrs = optionalAttributes(attrs,val);
    		return s+convertedAttrs;
    	}
    }

    
    String constructRelation(String predName, Object id, List<String> restArgs, Object keSet, Object attrs) {
    	
    	String convertedAttrs = new String();
    	String convertedKeSet = new String();
    	
    	Map<String, String> keSetMap = null;
    	List<String> keySet= null;
    	
    	if (keSet instanceof Map<?, ?>)
    	{
    		keSetMap = (Map<String, String>) keSet;
    	} else // expect List<Object>
    		keySet = (List<String>) keSet;
    	
    	boolean first = true;
    	StringBuffer s = new StringBuffer();
    	if (id == null) {
    		s.append(predName + "(" );
    	}
    	else {
    		s.append(predName + "(" + id);
    		first = false;
    	}
    	
    	if (restArgs != null) {
    		for (String arg : restArgs) {
    			if (!first) s.append(",");
    			s.append(arg);    		
    			first = false;
    		}	
    	}
    	
    	if (keSetMap != null && !keSetMap.isEmpty()) {
    		String keSetId = genId();
    		if (! first) s.append(",");
    		s.append(keSetId);
    		convertedKeSet = keyEntitySet(keSetMap,keSetId);
    	} else if (keySet != null && !keySet.isEmpty()) {
    		String keSetId = genId();
    		if (! first) s.append(",");
    		s.append(keSetId);
    		convertedKeSet = keySet(keySet,keSetId);
    	}

    	if (!attrs.equals("") ) {
    		String val = genId();
    		if (! first) s.append(",");
    		s.append(val+").\n");
    		convertedAttrs = optionalAttributes(attrs,val);
    	}
		return s+").\n"+convertedAttrs+convertedKeSet;
    }

    
    public String optionalTime(Object time) {
        return ((time==null)? ", nil" : (", " + time));
    }            

    public String optional(Object str) {
        return ((str==null)? "nil" : (String) str);
    }

    
    public Object convertActivity(Object id,Object startTime,Object endTime, Object aAttrs) {
    	
    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(startTime));
    	optionals.add(optional(endTime));
    	
    	return constructRelation("activity", id, optionals, aAttrs);
    }

    public Object convertEntity(Object id, Object attrs) {
    	    	return constructRelation("entity", id, null, attrs);
    }
    
    public Object convertAgent(Object id, Object attrs) {
    	return constructRelation("agent", id, null, attrs);
    }

    
    //wasDerivedFrom(tr2011:WD-prov-dm-20111215,tr2011:WD-prov-dm-20111018)
    public Object convertWasDerivedFrom(Object id, Object id2,Object id1, Object pe, Object g2, Object u1, Object aAttrs) {
    	
    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(id1));
    	optionals.add(optional(pe));
    	optionals.add(optional(g2));
    	optionals.add(optional(u1));

    	return constructRelation("wasDerivedFrom", id, optionals, aAttrs);
    	
//        String s="wasDerivedFrom(" + optionalId(id) + id2 + "," + id1 + 
//            ((pe==null) ? ", nil " : (", " + pe + ", " + g2 + "," + u1)) + optionalAttributes(aAttrs) +  ")";
//        return s;
    }

    public Object convertWasGeneratedBy(Object id, Object id2,Object id1, Object time, Object aAttrs ) {
    	
    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(id1));
    	optionals.add(optional(time));

    	return constructRelation("wasGeneratedBy", id, optionals, aAttrs);
    	
//        String s="wasGeneratedBy(" + optionalId(id) + id2 + "," + id1 +
//            optionalTime(time) + optionalAttributes(aAttrs) +  ")";
//        return s;
    }

    public Object convertUsed(Object id, Object id2,Object id1, Object time, Object aAttrs) {

    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(id1));
    	optionals.add(optional(time));

    	return constructRelation("used", id, optionals, aAttrs);

//    	String s="used(" + optionalId(id) + id2 + "," + id1 +
//            optionalTime(time) + optionalAttributes(aAttrs) + ")";
//        return s;
    }

    public Object convertWasAssociatedWith(Object id, Object id2,Object id1, Object pl, Object aAttrs) {
    	
    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(id1));
    	optionals.add(optional(pl));

    	return constructRelation("wasAssociatedWith", id, optionals, aAttrs);

//        String s="wasAssociatedWith(" + optionalId(id) + id2 + "," + id1 
//            + ((pl==null)? "" : " , " + pl) +
//            optionalAttributes(aAttrs) + ")";
//        return s;
    }

    public Object convertNamedBundle(Object id, Object nss, List<Object> records) {
        return null;
    }

    public Object convertBundle(Object namespaces, List<Object> records, List<Object> bundles) {
        String s="";
        for (Object o: records) {
            s=s+o+"\n";
        }
        return s;
    }
    

    public Object convertWasInformedBy(Object id, Object id2, Object id1, Object aAttrs) {

    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(id1));

    	return constructRelation("wasInformedBy", id, optionals, aAttrs);

    }

    

    public Object convertInsertion(Object id, Object id2, Object id1, Object kes, Object iAttrs) {
    	
    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(id1));
    	
    	return constructRelation("derivedByInsertionFrom", id, optionals, kes, iAttrs);
    }	

    
    

    public Object convertRemoval(Object id, Object id2, Object id1, Object keyset, Object rAttrs) {

    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(id1));
    	
    	return constructRelation("derivedByRemovalFrom", id, optionals, keyset, rAttrs);

//    	String s="derivedByRemovalFrom(" + optionalId(id) + id2 + ", " + id1 + ", "
//	    + keyset + optionalAttributes(rAttrs) +  ")";
//	return s;

    }
    
    
    public Object convertMemberOf(Object id, Object id2, Object kes, Object complete, Object mAttrs) {
    	
    	List<String> optionals = new ArrayList<String>();
    	optionals.add(optional(id2));
    	optionals.add(optional(complete));

    	return constructRelation("memberOf", id, optionals, kes, mAttrs);

//        String s="memberOf(" + optionalId(id) + id2 + ", "
//	    + kes + ((complete==null)? "" : ", "+complete) + optionalAttributes(mAttrs) +  ")";
//	return s;

    }

    
    
    public Object convertKeys(List<Object> keys) {
    	return keys;  // ID mapping
    }

    public Map<String, String>  convertKeyEntitySet(List<Object> entries) {
    	
    	Map<String, String> keSet = new HashMap<String, String>();
    	for (Object entry:entries) {
    		String strEntry = (String) entry;
    		String pairs = strEntry.substring(1, strEntry.length()-1);
    		java.util.StringTokenizer tok = new java.util.StringTokenizer(pairs, ",");
    		if (tok.countTokens()==2) {
    			keSet.put(tok.nextToken(), tok.nextToken());
    		}
    	}
    	return keSet;
    }

    
    /*****
     * OLD IMPLEMENTATION
     */
    

    // not used -- see optionalAttributes
    public Object convertAttributes(List<Object> attributes) {
        String s="";
        boolean first=true;
        for (Object o: attributes) {
            if (first) {
                first=false;
                s=s+o;
            } else {
                s=s+","+o;
            }
        }
        return s;
    }
    public Object convertId(String id) {
        return ((id==null)? id : id.replace(':','_'));
    }


    public Object convertAttribute(Object name, Object value) {
        return name + "=" + value;
    }
    public Object convertStart(String start) {
        return start;
    }
    public Object convertEnd(String end) {
        return end;
    }
    public Object convertA(Object a) {
        return a;
    }
    public Object convertU(Object a) {
        return a;
    }
    public Object convertG(Object a) {
        return a;
    }
    public Object convertString(String s) {
        return s;
    }

    public Object convertInt(int i) {
        return i;
    }

    public String optionalId(Object id) {
        return ((id==null)? "nil, " : (id + ","));
    }            


    public Object convertWasInvalidatedBy(Object id, Object id2,Object id1, Object time, Object aAttrs ) {
        String s="wasInvalidatedBy(" + optionalId(id) + id2 + "," + id1 +
            optionalTime(time) + optionalAttributes(aAttrs) +  ")";
        return s;
    }

    public Object convertWasStartedBy(Object id, Object id2,Object id1, Object id3, Object time, Object aAttrs ) {
        String s="wasStartedBy(" + optionalId(id) + id2 + "," + id1 + "," + id3 +
            optionalTime(time) + optionalAttributes(aAttrs) +  ")";
        return s;
    }

    public Object convertWasEndedBy(Object id, Object id2,Object id1, Object id3, Object time, Object aAttrs ) {
        String s="wasEndedBy(" + optionalId(id) + id2 + "," + id1 + "," + id3 +
            optionalTime(time) + optionalAttributes(aAttrs) +  ")";
        return s;
    }


<<<<<<< HEAD
    public Object convertWasStartedByActivity(Object id, Object id2, Object id1, Object aAttrs) {
        String s="wasStartedByActivity(" + optionalId(id) + id2 + "," + optional(id1)
                + optionalAttributes(aAttrs) +  ")";
            return s;
    }
=======

>>>>>>> upstream/master

    public Object convertWasAttributedTo(Object id, Object id2,Object id1, Object gAttrs) {
        String s="wasAttributedTo(" + optionalId(id) + id2 + "," + id1 + optionalAttributes(gAttrs) +  ")";
        return s;
    }

    public Object convertAlternateOf(Object id2, Object id1) {
        String s="alternateOf(" + id2 + "," + id1 + ")";
        return s;
    }

    public Object convertSpecializationOf(Object id2, Object id1) {
        String s="specializationOf(" + id2 + "," + id1 + ")";
        return s;
    }

	public Object convertActedOnBehalfOf(Object id, Object id2,Object id1, Object a, Object aAttrs) {
        String s="actedOnBehalfOf(" + optionalId(id) + id2 + "," + id1 + "," +
                optional(a) +
                optionalAttributes(aAttrs) + ")";
            return s;
    }

<<<<<<< HEAD
    public Object convertWasRevisionOf(Object id, Object id2,Object id1, Object ag, Object dAttrs) {
        String s="wasRevisionOf(" + optionalId(id) + id2 + ", " + id1 + ", " + optional(ag) + optionalAttributes(dAttrs) +  ")";
        return s;    
    }
    
    
    public Object convertWasQuotedFrom(Object id, Object id2,Object id1, Object ag2, Object ag1, Object dAttrs) {
        String s="wasQuotedFrom(" + optionalId(id) + id2 + ", " + id1 + ", " + optional(ag2) + ", " + optional(ag1) + optionalAttributes(dAttrs) +  ")";
        return s;
    }
    
    public Object convertHadOriginalSource(Object id, Object id2,Object id1, Object dAttrs) {
        String s="hadOriginalSource(" + optionalId(id) + id2 + ", " + id1 + optionalAttributes(dAttrs) +  ")";
        return s;
=======
    public Object convertWasAssociatedWith(Object id, Object id2,Object id1, Object pl, Object aAttrs) {
        String s="wasAssociatedWith(" + optionalId(id) + id2 + "," + id1 
            + ((pl==null)? "" : " , " + pl) +
            optionalAttributes(aAttrs) + ")";
        return s;
    }
    public Object convertWasRevisionOf(Object id, Object id2,Object id1, Object pe, Object q2, Object q1, Object dAttrs) {
        //todo
        throw new UnsupportedOperationException();
    }
    public Object convertWasQuotedFrom(Object id, Object id2,Object id1, Object pe, Object q2, Object q1, Object dAttrs) {
        //todo
        throw new UnsupportedOperationException();
    }
    public Object convertHadOriginalSource(Object id, Object id2,Object id1, Object pe, Object q2, Object q1, Object dAttrs) {
        //todo
        throw new UnsupportedOperationException();
>>>>>>> upstream/master
    }
    
    public Object convertTracedTo(Object id, Object id2, Object id1, Object dAttrs) {
        String s="tracedTo(" + optionalId(id) + id2 + ", " + id1 + optionalAttributes(dAttrs) +  ")";
        return s;
    }

    public Object convertQualifiedName(String qname) {
        return qname;
    }
    public Object convertIRI(String iri) {
        return iri;
    }

    public Object convertTypedLiteral(String datatype, Object value) {
        return value + "%%" + datatype;
    }

   public Object convertNamespace(Object pre, Object iri) {
       return "prefix " + pre + " " + iri;
   }

   public Object convertDefaultNamespace(Object iri) {
       return  "default " + iri;
   }

    public Object convertNamespaces(List<Object> namespaces) {
        String s="";
        for (Object o: namespaces) {
            s=s+o+"\n";
        }
        return s;
    }

    public Object convertPrefix(String pre) {
        return pre;
    }

   /* Component 5 */
        
        public Object convertEntry(Object o1, Object o2) {
            String s="{" + o1 + ", " + o2 + "}";
    	return s;
        }
        


        /* Component 6 */

        public Object convertNote(Object id, Object attrs) {
            String s="note(" + id  + optionalAttributes(attrs) + ")";
            return s;
        }

        public Object convertHasAnnotation(Object something, Object note) {
            String s="hasAnnotation(" + something  + "," + note + ")";
            return s;
        }


}