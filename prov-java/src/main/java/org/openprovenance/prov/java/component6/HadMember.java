package org.openprovenance.prov.java.component6;

import org.openprovenance.prov.java.component1.Entity;

public class HadMember {

	// Required fields
	private Collection collection;
	private Entity entity;

	public Collection getCollection() {
		return this.collection;
	}
	
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}
