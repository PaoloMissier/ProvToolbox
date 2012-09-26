package org.openprovenance.prov.java.component4;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openprovenance.prov.java.Element;
import org.openprovenance.prov.java.Record;
import org.openprovenance.prov.java.Relation;

public class Records {

	List<Element> elements = new ArrayList<Element>();
	List<Relation> relations = new ArrayList<Relation>();
	
	public boolean addRecord(Record record) {
		if (record instanceof Element) {
			return this.addElement((Element) record);
		} else if (record instanceof Relation) {
			return this.addRelation((Relation) record);
		}
		
		return false;
	}
	
	public boolean removeRecord(Record record) {
		if (record instanceof Element) {
			return this.removeElement((Element) record);
		} else if (record instanceof Relation) {
			return this.removeRelation((Relation) record);
		}
		
		return false;
	}
	
	public boolean addElement(Element element) {
		return this.elements.add(element);
	}
	
	public boolean removeElement(Element element) {
		return this.elements.remove(element);
	}
	
	public List<Element> getElements() {
		return this.elements;
	}
	
	public boolean addRelation(Relation relation) {
		return this.relations.add(relation);
	}
	
	public boolean removeRelation(Relation relation) {
		return this.relations.remove(relation);
	}
	
	public List<Relation> getRelations() {
		return relations;
	}
	
	private void toString(ToStringBuilder toStringBuilder) {
		toStringBuilder.append("elements", elements);
		toStringBuilder.append("relations", relations);
	}
	
	public String toString() {
		final ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		toString(toStringBuilder);
		return toStringBuilder.toString();
	}
}
