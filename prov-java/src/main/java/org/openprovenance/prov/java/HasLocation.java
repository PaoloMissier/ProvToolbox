package org.openprovenance.prov.java;

import java.util.List;

/**
 * Provides the ability to annotate PROV elements with a location. 
 * 
 * The PROV-N encoding of these is [prov:location = "location" ] e.g. [prov:location = "United Kingdom"]
 * @author William Martin
 *
 */
public interface HasLocation {

	public boolean addLocation(String location);
	public boolean removeLocation(String location);
	public List<String> getLocations();
}
