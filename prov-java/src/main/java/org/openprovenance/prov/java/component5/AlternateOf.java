package org.openprovenance.prov.java.component5;

import org.openprovenance.prov.java.AlternateRelation;
import org.openprovenance.prov.java.component1.Entity;

public class AlternateOf extends AlternateRelation {

	// Required Fields
	private Entity alternate1;
	private Entity alternate2;
	
	public Entity getAlternate1() {
		return this.alternate1;
	}
	
	public void setAlternate1(Entity alternate1) {
		this.alternate1 = alternate1;
		this.setStartElement(alternate1);
	}
	
	public Entity getAlternate2() {
		return this.alternate2;
	}
	
	public void setAlternate2(Entity alternate2) {
		this.alternate2 = alternate2;
		this.addEndElement(alternate2);
	}
}
