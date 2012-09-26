package org.openprovenance.prov.java.component1;

import org.openprovenance.prov.java.NonAlternateRelation;

public class WasInformedBy extends NonAlternateRelation {

	// Required Fields
	private Activity effect;
	private Activity cause;

	public Activity getEffect() {
		return this.effect;
	}
	
	public void setEffect(Activity effect) {
		this.effect = effect;
		this.setStartElement(effect);
	}
	
	public Activity getCause() {
		return this.cause;
	}
	
	public void setCause(Activity cause) {
		this.cause = cause;
		this.addEndElement(cause);
	}
	
}
