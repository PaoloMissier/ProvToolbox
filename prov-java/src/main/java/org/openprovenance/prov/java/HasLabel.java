package org.openprovenance.prov.java;

import java.util.List;

/**
 * Provides the ability to annotate PROV records with human readable label. 
 * All PROV records except those in Component 5 can be labelled.
 * 
 * The PROV-N encoding of these is [prov:label = "label" ] e.g. [prov:label = "An example label"]
 * @author William Martin
 *
 */
public interface HasLabel {

	public boolean addLabel(String label);
	public boolean removeLabel(String label);
	public List<String> getLabels();
}
