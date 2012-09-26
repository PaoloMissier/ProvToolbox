package org.openprovenance.prov.java.component1;

import org.openprovenance.prov.java.NonAlternateRelation;

public class WasStartedBy extends NonAlternateRelation {

	// Required Fields
	private Activity activity;
	
	// TODO: Make Time Strongly Typed
	// Optional Fields
	private Entity trigger;
	private Activity starter;
	private String time;
	
	public Activity getActivity() {
		return this.activity;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
		this.setStartElement(activity);
	}
	
	public Entity getTrigger() {
		return this.trigger;
	}
	
	public void setTrigger(Entity trigger) {
		this.trigger = trigger;

		if (trigger != null) {
			this.addEndElement(trigger);
		}
	}
	
	public Activity getStarter() {
		return this.starter;
	}
	
	public void setStarter(Activity starter) {
		this.starter = starter;
		
		if (starter != null) {
			this.addEndElement(starter);
		}
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}
