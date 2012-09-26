package org.openprovenance.prov.java;

import java.util.List;

import org.openprovenance.prov.java.component1.Activity;
import org.openprovenance.prov.java.component1.Entity;
import org.openprovenance.prov.java.component1.Used;
import org.openprovenance.prov.java.component1.WasGeneratedBy;
import org.openprovenance.prov.java.component3.Agent;

/**
 * An interface for constructing data structures when JProv is traversed by the JProvTraversal class.
**/
public interface JProvConstructor {

	 public Object convert(String s);

	 // Component 1
	 public Object convertEntity(Object id, List<Object> tAttrs, List<Object> lAttr, List<Object> otherAttrs);
	 public Object convertActivity(Object id, List<Object> tAttrs, List<Object> lAttr, List<Object> otherAttrs, Object startTime, Object endTime);
	 
	 public Object convertUsed(Object id, List<Object> tAttrs, List<Object> otherAttrs, Activity activity, Entity entity, Object time);
	 public Object convertWasGeneratedBy(Object id, List<Object> tAttrs, List<Object> otherAttrs, Entity entity, Activity activity, Object time);
	 public Object convertWasStartedBy(Object id, List<Object> tAttrs, List<Object> otherAttrs, Activity activity, Entity entity, Activity starter, Object time);
	 public Object convertWasEndedBy(Object id, List<Object> tAttrs, List<Object> otherAttrs, Activity activity, Entity entity, Activity ender, Object time);
	 public Object convertWasInvalidatedBy(Object id, List<Object> tAttrs, List<Object> otherAttrs, Entity entity, Activity activity, Object time);
	 public Object convertWasInformedBy(Object id, List<Object> tAttrs, List<Object> otherAttrs, Activity effect, Activity cause);
	
	 // Component 2
	 
	 public Object convertWasDerivedFrom(Object id, List<Object> tAttrs, List<Object> otherAttrs, Entity generatedEntity, Entity usedEntity, Activity activity, WasGeneratedBy generation, Used usage);

	 // Component 3
	 
	 public Object convertAgent(Object id, List<Object> tAttrs, List<Object> lAttr, List<Object> otherAttrs);
	 public Object convertWasInfluencedBy(Object id, List<Object> tAttrs, List<Object> otherAttrs, Agent influencee, Agent influencer);
	 public Object convertWasAssociatedWith(Object id, List<Object> tAttrs, List<Object> otherAttrs, Activity activity, Agent agent, Entity plan);
	 public Object convertWasAttributedTo(Object id, List<Object> tAttrs, List<Object> otherAttrs, Entity entity, Agent agent);
	 public Object convertActedOnBehalfOf(Object id, List<Object> tAttrs, List<Object> otherAttrs, Agent subordinate, Agent responsible, Activity activity);

	 // Component 5
	 
	 public Object convertAlternateOf(Entity alternate1, Entity alternate2);
	 public Object convertSpecializationOf(Entity specializedEntity, Entity generalEntity);
	 public Object convertMentionOf(Entity specializedEntity, Entity generalEntity, Entity bundle);

	 public Object convertAttribute(Object name, Object value);
	 public Object convertTypedLiteral(String datatype, Object value);
	 public Object convertBundle(Object namespaces,
					List<Object> aRecords,
					List<Object> eRecords,
					List<Object> agRecords,
					List<Object> lnkRecords,
					List<Object> bRecords);
	   
	 public Object convertNamedBundle(Object id,
					     Object namespaces,
	                                    List<Object> aRecords,
	                                    List<Object> eRecords,
	                                    List<Object> agRecords,
	                                    List<Object> lnkRecords);

}
