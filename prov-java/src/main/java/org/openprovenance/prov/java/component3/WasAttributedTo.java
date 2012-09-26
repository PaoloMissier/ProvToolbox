package org.openprovenance.prov.java.component3;

import org.openprovenance.prov.java.NonAlternateRelation;
import org.openprovenance.prov.java.component1.Entity;

public class WasAttributedTo extends NonAlternateRelation {

	// Required Fields
	private Entity entity;
	private Agent agent;
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
		this.setStartElement(entity);
	}
	
	public Agent getAgent() {
		return this.agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
		this.addEndElement(agent);
	}
}
