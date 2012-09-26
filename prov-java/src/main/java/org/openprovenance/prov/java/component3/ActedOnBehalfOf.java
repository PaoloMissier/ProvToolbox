package org.openprovenance.prov.java.component3;

import org.openprovenance.prov.java.NonAlternateRelation;
import org.openprovenance.prov.java.component1.Activity;

public class ActedOnBehalfOf extends NonAlternateRelation {

	// Required Fields
	private Agent subordinate;
	private Agent responsible;
	
	// Optional Fields
	private Activity activity;
	
	public Agent getSubordinate() {
		return this.subordinate;
	}
	
	public void setSubordinate(Agent subordinate) {
		this.subordinate = subordinate;
		this.setStartElement(subordinate);
	}
	
	public Agent getResponsible() {
		return this.responsible;
	}
	
	public void setResponsible(Agent responsible) {
		this.responsible = responsible;
		this.addEndElement(responsible);
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
}
