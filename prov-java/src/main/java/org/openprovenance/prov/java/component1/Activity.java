package org.openprovenance.prov.java.component1;

import java.util.Map;

import org.antlr.runtime.tree.CommonTree;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.openprovenance.prov.java.Element;

/**
 * The base class for all PROV Activities.
 * In PROV-N encoding: activity()
 * @author William Martin
 *
 */
public class Activity extends Element {
	
	// TODO: Make Strongly Typed Times
	private String startTime;
	private String endTime;
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	private void toString(ToStringBuilder toStringBuilder) {
		toStringBuilder.appendSuper(super.toString());
		toStringBuilder.append("startTime", startTime);
		toStringBuilder.append("endTime", endTime);
	}
	
	public String toString() {
		final ToStringBuilder toStringBuilder = new ToStringBuilder(this);
		toString(toStringBuilder);
		return toStringBuilder.toString();
	}
}
