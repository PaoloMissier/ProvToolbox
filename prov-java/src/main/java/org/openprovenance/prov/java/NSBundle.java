/**
 * 
 */
package org.openprovenance.prov.java;

import java.util.HashMap;
import java.util.Map;

import org.openprovenance.prov.java.component4.Bundle;

/**
 * @author paolo
 *
 */
public class NSBundle extends Bundle {

	private Map<Object, Object> namespaces = new HashMap<Object, Object>();

	/**
	 * adds explicit namespaces 
	 */
	public NSBundle() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the namespaces
	 */
	public Map<Object, Object> getNamespaces() {
		return namespaces;
	}

	/**
	 * @param namespaces the namespaces to set
	 */
	public void setNamespaces(Map<Object, Object> namespaces) {
		this.namespaces = namespaces;
	}

}
