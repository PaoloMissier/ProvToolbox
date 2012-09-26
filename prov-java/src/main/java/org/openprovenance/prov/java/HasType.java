package org.openprovenance.prov.java;

import java.util.List;

/**
 * Provides the ability to annotate PROV elements with a type, extending the core PROV records.
 * 
 * The PROV-N encoding of these is [prov:type = "" ] e.g. [prov:type = "Organization"]
 * @author William Martin
 *
 */
public interface HasType {

	public boolean addType(String type);
	public boolean removeType(String type);
	public List<String> getTypes();
}
