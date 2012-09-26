package org.openprovenance.prov.java.component3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openprovenance.prov.java.HasPlan;
import org.openprovenance.prov.java.HasRole;
import org.openprovenance.prov.java.NonAlternateRelation;
import org.openprovenance.prov.java.component1.Activity;
import org.openprovenance.prov.java.component1.Entity;

public class WasAssociatedWith extends NonAlternateRelation implements HasRole, HasPlan {

	// Required Fields
	private Activity activity;
	
	// Optional Fields
	private Agent agent;
	private Entity plan;

	private List<String> roles = new ArrayList<String>();
	
	public Activity getActivity() {
		return this.activity;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
		this.setStartElement(activity);
	}
	
	public Agent getAgent() {
		return this.agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
		
		if (agent != null) {
			this.addEndElement(agent);
		}
	}
	
	@Override
	public Entity getPlan() {
		return this.plan;
	}

	@Override
	public void setPlan(Entity plan) {
		this.plan = plan;
		
		if (this.plan != null) {
			this.addEndElement(plan);
		}
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
