package org.openprovenance.prov.java.component1;

import org.openprovenance.prov.java.NonAlternateRelation;

public class WasInvalidatedBy extends NonAlternateRelation {
	
	// Required Fields
	private Entity entity;
	
	// TODO: Constraint upon one of activity, time or extra attributes being valid
	// Optional Fields
	private Activity activity;
	private String time;
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
		this.setStartElement(entity);
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
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}
