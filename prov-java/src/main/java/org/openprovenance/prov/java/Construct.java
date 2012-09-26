package org.openprovenance.prov.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Base Class for many PROV records.
 * All PROV records except those in Component 5 have identifiers, labels, types and extensible attributes
 * @author William Martin
 *
 */
public abstract class Construct implements Identifiable, HasLabel, HasType, HasAny, Record {

	private String id;
	private List<String> labels = new ArrayList<String>();
	private List<String> types = new ArrayList<String>();
	private Map<String, String> any = new TreeMap<String, String>();

	@Override
	public void setId(String id) {
		this.id = id;		
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public boolean addLabel(String label) {
		return this.labels.add(label);
	}

	@Override
	public boolean removeLabel(String label) {
		return this.labels.remove(label);
	}

	@Override
	public List<String> getLabels() {
		return Collections.unmodifiableList(this.labels);
	}
	
	@Override
	public boolean addType(String type) {
		return this.types.add(type);
	}

	@Override
	public boolean removeType(String type) {
		return this.types.remove(type);
	}

	@Override
	public List<String> getTypes() {
		return Collections.unmodifiableList(this.types);
	}
	
	@Override
	public String addValue(String key, String value) {
		return this.any.put(key, value);
	}

	@Override
	public String removeValue(String key) {
		return this.any.remove(key);
	}

	@Override
	public String getValue(String key) {
		return this.any.get(key);
	}
	
	@Override
	public Map<String, String> getValues() {
		return Collections.unmodifiableMap(this.any);
	}
	
	private void equals(Object object, EqualsBuilder equalsBuilder) {
		if (!(object instanceof Construct)) {
			equalsBuilder.appendSuper(false);
			return;
		}
		if (this == object) {
			return;
		}
		final Construct that = ((Construct) object);
		equalsBuilder.append(this.getLabels(), that.getLabels());
		equalsBuilder.append(this.getTypes(), that.getTypes());
		equalsBuilder.append(this.getValues(), that.getValues());
		equalsBuilder.append(this.getId(), that.getId());
	}
	
	public boolean equals(Object object) {
		if (!(object instanceof Construct)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		final EqualsBuilder equalsBuilder = new EqualsBuilder();
		equals(object, equalsBuilder);
		return equalsBuilder.isEquals();
	}
	
	private void hashCode(HashCodeBuilder hashCodeBuilder) {
		hashCodeBuilder.append(this.getLabels());
		hashCodeBuilder.append(this.getTypes());
		hashCodeBuilder.append(this.getValues());
		hashCodeBuilder.append(this.getId());
	}
	
	public int hashCode() {
		final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		this.hashCode(hashCodeBuilder);
		return hashCodeBuilder.toHashCode();
	}
	
	private void toString(ToStringBuilder toStringBuilder) {
		toStringBuilder.append("label", this.getLabels());
		toStringBuilder.append("type", this.getTypes());
		toStringBuilder.append("any", this.getValues());
		toStringBuilder.append("id", this.getId());
	}
	
	public String toString() {
		final ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		toString(toStringBuilder);
		return toStringBuilder.toString();
	}

}
