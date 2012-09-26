package org.openprovenance.prov.java.component3;

import org.openprovenance.prov.java.NonAlternateRelation;
import org.openprovenance.prov.java.component1.Activity;

public class WasInfluencedBy extends NonAlternateRelation {

	// Required Fields
	private Agent influencee;
	private Agent influencer;
	
	// Optional Fields
	private Activity activity;
	
	public Agent getInfluencee() {
		return this.influencee;
	}
	
	public void setInfluencee(Agent influencee) {
		this.influencee = influencee;
		this.setStartElement(influencee);
	}
	
	public Agent getInfluencer() {
		return this.influencer;
	}
	
	public void setInfluencer(Agent influencer) {
		this.influencer = influencer;
		this.addEndElement(influencer);
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
