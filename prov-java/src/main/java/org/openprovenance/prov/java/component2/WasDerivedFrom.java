package org.openprovenance.prov.java.component2;

import org.openprovenance.prov.java.NonAlternateRelation;
import org.openprovenance.prov.java.component1.Activity;
import org.openprovenance.prov.java.component1.Entity;
import org.openprovenance.prov.java.component1.Used;
import org.openprovenance.prov.java.component1.WasGeneratedBy;

public class WasDerivedFrom extends NonAlternateRelation {

	// Required Fields
	private Entity generatedEntity;
	private Entity usedEntity;
	
	// Optional Fields
	private Activity activity;
	private WasGeneratedBy generation;
	private Used usage;
	
	public Entity getGeneratedEntity() {
		return this.generatedEntity;
	}
	
	public void setGeneratedEntity(Entity generatedEntity) {
		this.generatedEntity = generatedEntity;
		this.setStartElement(generatedEntity);
	}
	
	public Entity getUsedEntity() {
		return this.usedEntity;
	}
	
	public void setUsedEntity(Entity usedEntity) {
		this.usedEntity = usedEntity;
		this.addEndElement(usedEntity);
	}
	
	public Activity getActivity() {
		return this.activity;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
		
		if (activity != null) {
			this.addEndElement(activity);
		}
	}
	
	public WasGeneratedBy getGeneration() {
		return this.generation;
	}
	
	public void setGeneration(WasGeneratedBy generation) {
		this.generation = generation;
	}
	
	public Used getUsage() {
		return this.usage;
	}
	
	public void setUsage(Used usage) {
		this.usage = usage;
	}
}
