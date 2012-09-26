package org.openprovenance.prov.java;

import java.util.List;

/**
 * To provide traversability, all relations in PROV must provide a start 
 * and ending elements. Unfortunately, not all relations are binary and most are
 * n-ary (one to many) which can cause some surprising API naming.
 * @author William Martin
 *
 */
public interface Relation extends Record {

	public Element getStartElement();
	public List<Element> getEndElements();
}
