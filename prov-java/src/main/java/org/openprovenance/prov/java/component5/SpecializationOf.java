package org.openprovenance.prov.java.component5;

import org.openprovenance.prov.java.AlternateRelation;
import org.openprovenance.prov.java.component1.Entity;

public class SpecializationOf extends AlternateRelation {

	// Required Fields
	private Entity specializedEntity;
	private Entity generalEntity;
	
	public Entity getSpecializedEntity() {
		return this.specializedEntity;
	}
	
	public void setSpecializedEntity(Entity specializedEntity) {
		this.specializedEntity = specializedEntity;
		this.setStartElement(specializedEntity);
	}
	
	public Entity getGeneralEntity() {
		return this.generalEntity;
	}
	
	public void setGeneralEntity(Entity generalEntity) {
		this.generalEntity = generalEntity;
		this.addEndElement(generalEntity);
	}
}
