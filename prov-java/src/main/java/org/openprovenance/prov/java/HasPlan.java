package org.openprovenance.prov.java;

import org.openprovenance.prov.java.component1.Entity;

/**
 * Provides the ability to relate PROV records to a Plan
 * 
 * @author William Martin
 *
 */
public interface HasPlan {

	public Entity getPlan();
	public void setPlan(Entity plan);
}
