package org.openprovenance.prov.java.component1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openprovenance.prov.java.HasRole;
import org.openprovenance.prov.java.NonAlternateRelation;

public class Used extends NonAlternateRelation implements HasRole {
	
	// Required fields
	private Activity activity;

	// TODO: Make Strongly Typed Time
	// Optional fields
	private Entity entity;
	private String time;
	
	private List<String> roles = new ArrayList<String>();
	
	public Activity getActivity() {
		return this.activity;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
		this.setStartElement(activity);
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
		
		if (entity != null) {
			this.addEndElement(entity);
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
