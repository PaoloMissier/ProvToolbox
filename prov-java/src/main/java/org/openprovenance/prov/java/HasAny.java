package org.openprovenance.prov.java;

import java.util.Map;

/**
 * Provides the ability to extend PROV records. All PROV records except those in
 * Component 5 have the ability to be extended.
 * 
 * The PROV-N encoding of these is [key = "value"] e.g. [country = "England"]
 * @author William Martin
 *
 */
public interface HasAny {

	public String addValue(String key, String value);
	public String removeValue(String key);
	public String getValue(String key);
	public Map<String, String> getValues();
}
