package org.openprovenance.prov.java.component1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openprovenance.prov.java.HasRole;
import org.openprovenance.prov.java.NonAlternateRelation;

public class WasGeneratedBy extends NonAlternateRelation implements HasRole {
	
	// Required Fields
	private Entity entity;
	
	// TODO: Make Strongly Typed Time
	// Optional Fields
	private Activity activity;
	private String time;
	
	private List<String> roles = new ArrayList<String>();
	
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
	
	@Override
	public boolean addRole(String role) {
		return this.roles.add(role);
	}

	@Override
	public boolean removeRole(String role) {
		return this.roles.remove(role);
	}

	@Override
	public List<String> getRoles() {
		return Collections.unmodifiableList(this.roles);
	}
}
