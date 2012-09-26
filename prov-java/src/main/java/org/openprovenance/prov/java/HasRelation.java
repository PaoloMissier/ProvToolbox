package org.openprovenance.prov.java;

import java.util.List;

/**
 * Unrelated to the PROV-DM, this requires implementing elements to keep a list of
 * associated relations to allow for easy traversal of the graph. 
 * @author William Martin
 *
 */
public interface HasRelation {

	public boolean addRelation(Relation relation);
	public boolean removeRelation(Relation relation);
	public List<Relation> getRelations();
}
