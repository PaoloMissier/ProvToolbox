package org.openprovenance.prov.xml;
import java.util.List;

public interface HasExtensibility {
	public List<Attribute> getAny();
	public java.util.Hashtable<String,List<Attribute>> attributesWithNamespace(String namespace);
} 
