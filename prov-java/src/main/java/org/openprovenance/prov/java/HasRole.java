package org.openprovenance.prov.java;

import java.util.List;

/**
 * Provides the ability for PROV records to be annotated with a role. For example, 
 * the author of this class:
 * 
 * prov:role = "Author"
 * @author William Martin
 *
 */
public interface HasRole {

	public boolean addRole(String role);
	public boolean removeRole(String role);
	public List<String> getRoles();
}
