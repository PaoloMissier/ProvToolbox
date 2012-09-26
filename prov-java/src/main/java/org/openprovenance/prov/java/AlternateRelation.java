package org.openprovenance.prov.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Relations under Component 5 do not allow attribute pairs to be applied and as such
 * must not extend Construct. 
 * @author William Martin
 *
 */
public abstract class AlternateRelation implements Relation {

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
