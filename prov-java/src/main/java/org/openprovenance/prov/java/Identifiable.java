package org.openprovenance.prov.java;

/**
 * Provides the ability to identify PROV records. All PROV records except those in
 * Component 5 are identifiable.
 * @author William Martin
 *
 */
public interface Identifiable {

	public void setId(String id);
	public String getId();
}
