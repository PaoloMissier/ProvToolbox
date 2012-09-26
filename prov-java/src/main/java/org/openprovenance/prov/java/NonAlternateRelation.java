package org.openprovenance.prov.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Any relations not under Component 5 extend Construct, providing identifiers
 * labels, types and extensibility attributes.
 * @author William Martin
 *
 */
public abstract class NonAlternateRelation extends Construct implements Relation {

	private Element startElement;
	private List<Element> endElements = new ArrayList<Element>();
	
	@Override
	public Element getStartElement() {
		return this.startElement;
	}
	
	protected void setStartElement(Element element) {
		this.startElement = element;
	}
	
	protected void addEndElement(Element element) {
		this.endElements.add(element);
	}
	
	protected void removeEndElement(Element element) {
		this.endElements.remove(element);
	}

	@Override
	public List<Element> getEndElements() {
		return this.endElements;
	}

}
