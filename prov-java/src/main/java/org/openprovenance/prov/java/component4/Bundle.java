package org.openprovenance.prov.java.component4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openprovenance.prov.java.Record;
import org.openprovenance.prov.java.component1.Entity;

public class Bundle extends Entity {

	private Records records = new Records();
	private List<Bundle> bundles = new ArrayList<Bundle>();
	
	public Bundle() {
		this.addType("prov:Bundle");
	}
	
	public boolean addRecord(Record record) {
		return records.addRecord(record);
	}
	
	public boolean removeRecord(Record record) {
		return records.removeRecord(record);
	}
	
	public Records getRecords() {
		return this.records;
	}
	
	public boolean addBundle(Bundle bundle) {
		return this.bundles.add(bundle);
	}
	
	public boolean removeBundle(Bundle bundle) {
		return this.bundles.remove(bundle);
	}
	
	public List<Bundle> getBundles() {
		return Collections.unmodifiableList(bundles);
	}
	
	private void toString(ToStringBuilder toStringBuilder) {
		toStringBuilder.appendSuper(super.toString());
		toStringBuilder.append("records", records);
		toStringBuilder.append("bundles", bundles);
	}
	
	public String toString() {
		final ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		toString(toStringBuilder);
		return toStringBuilder.toString();
	}
			
}
