package org.openprovenance.prov.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Base Class for Entities, Activities, Agents and all their subclasses
 * In addition to the interfaces implemented in Construct, all PROV elements have
 * locations. External to PROV itself all elements must provide the ability to traverse
 * the graph through the HasRelation interface.
 * @author William Martin
 *
 */
public abstract class Element extends Construct implements HasLocation, HasRelation {

	private List<String> locations = new ArrayList<String>();
	private List<Relation> relations = new ArrayList<Relation>();
	
	@Override
	public boolean addLocation(String location) {
		return this.locations.add(location);
	}

	@Override
	public boolean removeLocation(String location) {
		return this.locations.remove(location);
	}

	@Override
	public List<String> getLocations() {
		return Collections.unmodifiableList(this.locations);
	}
	
	@Override
	public boolean addRelation(Relation relation) {
		return this.relations.add(relation);
	}

	@Override
	public boolean removeRelation(Relation relation) {
		return this.relations.remove(relation);
	}

	@Override
	public List<Relation> getRelations() {
		return Collections.unmodifiableList(this.relations);
	}
	
	private void toString(ToStringBuilder toStringBuilder) {
		toStringBuilder.appendSuper(super.toString());
		toStringBuilder.append("location", locations);
	}
	
	public String toString() {
		final ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		toString(toStringBuilder);
		return toStringBuilder.toString();
	}
}
