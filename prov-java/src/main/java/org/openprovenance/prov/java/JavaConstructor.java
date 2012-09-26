package org.openprovenance.prov.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openprovenance.prov.java.component1.Activity;
import org.openprovenance.prov.java.component1.Entity;
import org.openprovenance.prov.java.component1.Used;
import org.openprovenance.prov.java.component1.WasEndedBy;
import org.openprovenance.prov.java.component1.WasGeneratedBy;
import org.openprovenance.prov.java.component1.WasInformedBy;
import org.openprovenance.prov.java.component1.WasInvalidatedBy;
import org.openprovenance.prov.java.component1.WasStartedBy;
import org.openprovenance.prov.java.component2.HadPrimarySource;
import org.openprovenance.prov.java.component2.WasDerivedFrom;
import org.openprovenance.prov.java.component2.WasQuotedFrom;
import org.openprovenance.prov.java.component2.WasRevisionOf;
import org.openprovenance.prov.java.component3.ActedOnBehalfOf;
import org.openprovenance.prov.java.component3.Agent;
import org.openprovenance.prov.java.component3.WasAssociatedWith;
import org.openprovenance.prov.java.component3.WasAttributedTo;
import org.openprovenance.prov.java.component3.WasInfluencedBy;
import org.openprovenance.prov.java.component4.Bundle;
import org.openprovenance.prov.java.component5.AlternateOf;
import org.openprovenance.prov.java.component5.MentionOf;
import org.openprovenance.prov.java.component5.SpecializationOf;
import org.openprovenance.prov.notation.TreeConstructor;

public class JavaConstructor implements TreeConstructor {

	Map<String, Entity> entities = new HashMap<String, Entity>();
	Map<String, Activity> activities = new HashMap<String, Activity>();
	Map<String, Agent> agents = new HashMap<String, Agent>();
	
	// Put any relations with ids in here so we can get them later
	Map<String, Relation> relations = new HashMap<String, Relation>();

	// Component 1

	@Override
	public Object convertEntity(Object id, Object attrs) {
		Entity entity = new Entity();
		entity.setId((String) id);
		String v = "" + attrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					entity.addLabel(value);
				} else if (key.equals("prov:type")) {
					entity.addType(value);
				} else if (key.equals("prov:location")) {
					entity.addLocation(value);
				} else {
					entity.addValue(key, value);
				}
			}
			break;
		}

		entities.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public Object convertActivity(Object id, Object startTime, Object endTime,
			Object aAttrs) {
		Activity activity = new Activity();
		activity.setId((String) id);
		activity.setStartTime((String) startTime);
		activity.setEndTime((String) endTime);
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					activity.addLabel(value);
				} else if (key.equals("prov:type")) {
					activity.addType(value);
				} else if (key.equals("prov:location")) {
					activity.addLocation(value);
				} else {
					activity.addValue(key, value);
				}
			}
			break;
		}

		activities.put(activity.getId(), activity);
		return activity;
	}

	@Override
	public Object convertStart(String start) {
		return start;
	}

	@Override
	public Object convertEnd(String end) {
		return end;
	}

	@Override
	public Object convertUsed(Object id, Object id2, Object id1, Object time,
			Object aAttrs) {
		Used used = new Used();
		
		Activity activity = activities.get((String) id2);
		Entity entity = entities.get((String) id1);
		
		activity.addRelation(used);
		
		used.setId((String) id);
		used.setActivity(activity);
		used.setEntity(entity);
		used.setTime((String) time);

		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					used.addLabel(value);
				} else if (key.equals("prov:type")) {
					used.addType(value);
				} else if (key.equals("prov:role")) {
					used.addRole(value);
				} else {
					used.addValue(key, value);
				}
			}
			break;
		}

		if (used.getId() != null) {
			relations.put(used.getId(), used);
		}
		return used;
	}

	@Override
	public Object convertWasGeneratedBy(Object id, Object id2, Object id1,
			Object time, Object aAttrs) {
		WasGeneratedBy wasGeneratedBy = new WasGeneratedBy();
		
		Entity entity = entities.get((String) id2);
		Activity activity = activities.get((String) id1);

		entity.addRelation(wasGeneratedBy);
		
		wasGeneratedBy.setId((String) id);
		wasGeneratedBy.setEntity(entity);
		wasGeneratedBy.setActivity(activity);
		wasGeneratedBy.setTime((String) time);

		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasGeneratedBy.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasGeneratedBy.addType(value);
				} else if (key.equals("prov:role")) {
					wasGeneratedBy.addRole(value);
				} else {
					wasGeneratedBy.addValue(key, value);
				}
			}
			break;
		}

		if (wasGeneratedBy.getId() != null) {
			relations.put(wasGeneratedBy.getId(), wasGeneratedBy);
		}
		return wasGeneratedBy;
	}

	@Override
	public Object convertWasStartedBy(Object id, Object id2, Object id1,
			Object id3, Object time, Object aAttrs) {
		
		WasStartedBy wasStartedBy = new WasStartedBy();
		
		Activity activity = activities.get((String) id2);
		Entity trigger = entities.get((String) id1);
		Activity starter = activities.get((String) id3);
		
		activity.addRelation(wasStartedBy);
		
		wasStartedBy.setId((String) id);
		wasStartedBy.setActivity(activity);
		wasStartedBy.setTrigger(trigger);
		wasStartedBy.setStarter(starter);
		wasStartedBy.setTime((String) time);
		
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasStartedBy.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasStartedBy.addType(value);
				} else {
					wasStartedBy.addValue(key, value);
				}
			}
			break;
		}

		if (wasStartedBy.getId() != null) {
			relations.put(wasStartedBy.getId(), wasStartedBy);
		}
		return wasStartedBy;
	}

	@Override
	public Object convertWasEndedBy(Object id, Object id2, Object id1,
			Object id3, Object time, Object aAttrs) {
		
		WasEndedBy wasEndedBy = new WasEndedBy();
		
		Activity activity = activities.get((String) id2);
		Entity trigger = entities.get((String) id1);
		Activity ender = activities.get((String) id3);
		
		activity.addRelation(wasEndedBy);
		
		wasEndedBy.setId((String) id);
		wasEndedBy.setActivity(activity);
		wasEndedBy.setTrigger(trigger);
		wasEndedBy.setEnder(ender);
		wasEndedBy.setTime((String) time);
		
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasEndedBy.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasEndedBy.addType(value);
				} else {
					wasEndedBy.addValue(key, value);
				}
			}
			break;
		}

		if (wasEndedBy.getId() != null) {
			relations.put(wasEndedBy.getId(), wasEndedBy);
		}
		return wasEndedBy;
	}

	@Override
	public Object convertWasInvalidatedBy(Object id, Object id2, Object id1,
			Object time, Object aAttrs) {
		
		WasInvalidatedBy wasInvalidatedBy = new WasInvalidatedBy();
		
		Entity entity = entities.get((String) id2);
		Activity activity = activities.get((String) id1);
		
		entity.addRelation(wasInvalidatedBy);
		
		wasInvalidatedBy.setId((String) id);
		wasInvalidatedBy.setEntity(entity);
		wasInvalidatedBy.setActivity(activity);
		wasInvalidatedBy.setTime((String) time);
		
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasInvalidatedBy.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasInvalidatedBy.addType(value);
				} else {
					wasInvalidatedBy.addValue(key, value);
				}
			}
			break;
		}

		if (wasInvalidatedBy.getId() != null) {
			relations.put(wasInvalidatedBy.getId(), wasInvalidatedBy);
		}
		return wasInvalidatedBy;
	}

	@Override
	public Object convertWasInformedBy(Object id, Object id2, Object id1,
			Object aAttrs) {
		
		WasInformedBy wasInformedBy = new WasInformedBy();
		
		Activity effect = activities.get((String) id2);
		Activity cause = activities.get((String) id1);
		
		effect.addRelation(wasInformedBy);
		
		wasInformedBy.setId((String) id);
		wasInformedBy.setEffect(effect);
		wasInformedBy.setCause(cause);
		
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasInformedBy.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasInformedBy.addType(value);
				} else {
					wasInformedBy.addValue(key, value);
				}
			}
			break;
		}

		if (wasInformedBy.getId() != null) {
			relations.put(wasInformedBy.getId(), wasInformedBy);
		}
		return wasInformedBy;
	}
	
	// Component 2

	@Override
	public Object convertWasDerivedFrom(Object id, Object id2, Object id1,
			Object pe, Object q2, Object q1, Object dAttrs) {
		
		WasDerivedFrom wasDerivedFrom = new WasDerivedFrom();
		
		Entity generatedEntity = entities.get((String) id2);
		Entity usedEntity = entities.get((String) id1);
		Activity activity = activities.get((String) pe);
		WasGeneratedBy wasGeneratedBy = (WasGeneratedBy) relations.get((String) q2);
		Used used = (Used) relations.get((String) q1);
		
		generatedEntity.addRelation(wasDerivedFrom);
		
		wasDerivedFrom.setId((String) id);
		wasDerivedFrom.setGeneratedEntity(generatedEntity);
		wasDerivedFrom.setUsedEntity(usedEntity);
		wasDerivedFrom.setActivity(activity);
		wasDerivedFrom.setGeneration(wasGeneratedBy);
		wasDerivedFrom.setUsage(used);
		
		String v = "" + dAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasDerivedFrom.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasDerivedFrom.addType(value);
				} else {
					wasDerivedFrom.addValue(key, value);
				}
			}
			break;
		}

		if (wasDerivedFrom.getId() != null) {
			relations.put(wasDerivedFrom.getId(), wasDerivedFrom);
		}
		return wasDerivedFrom;
	}

	@Override
	public Object convertWasRevisionOf(Object id, Object id2, Object id1,
			Object pe, Object q2, Object q1, Object dAttrs) {
		
		WasRevisionOf wasRevisionOf = new WasRevisionOf();
		
		Entity generatedEntity = entities.get((String) id2);
		Entity usedEntity = entities.get((String) id1);
		Activity activity = activities.get((String) pe);
		WasGeneratedBy wasGeneratedBy = (WasGeneratedBy) relations.get((String) q2);
		Used used = (Used) relations.get((String) q1);
		
		generatedEntity.addRelation(wasRevisionOf);
		
		wasRevisionOf.setId((String) id);
		wasRevisionOf.setGeneratedEntity(generatedEntity);
		wasRevisionOf.setUsedEntity(usedEntity);
		wasRevisionOf.setActivity(activity);
		wasRevisionOf.setGeneration(wasGeneratedBy);
		wasRevisionOf.setUsage(used);
		
		String v = "" + dAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasRevisionOf.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasRevisionOf.addType(value);
				} else {
					wasRevisionOf.addValue(key, value);
				}
			}
			break;
		}

		if (wasRevisionOf.getId() != null) {
			relations.put(wasRevisionOf.getId(), wasRevisionOf);
		}
		return wasRevisionOf;
	}

	@Override
	public Object convertWasQuotedFrom(Object id, Object id2, Object id1,
			Object pe, Object q2, Object q1, Object dAttrs) {
		
		WasQuotedFrom wasQuotedFrom = new WasQuotedFrom();
		
		Entity generatedEntity = entities.get((String) id2);
		Entity usedEntity = entities.get((String) id1);
		Activity activity = activities.get((String) pe);
		WasGeneratedBy wasGeneratedBy = (WasGeneratedBy) relations.get((String) q2);
		Used used = (Used) relations.get((String) q1);
		
		generatedEntity.addRelation(wasQuotedFrom);
		
		wasQuotedFrom.setId((String) id);
		wasQuotedFrom.setGeneratedEntity(generatedEntity);
		wasQuotedFrom.setUsedEntity(usedEntity);
		wasQuotedFrom.setActivity(activity);
		wasQuotedFrom.setGeneration(wasGeneratedBy);
		wasQuotedFrom.setUsage(used);
		
		String v = "" + dAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasQuotedFrom.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasQuotedFrom.addType(value);
				} else {
					wasQuotedFrom.addValue(key, value);
				}
			}
			break;
		}

		if (wasQuotedFrom.getId() != null) {
			relations.put(wasQuotedFrom.getId(), wasQuotedFrom);
		}
		return wasQuotedFrom;
	}

	@Override
	public Object convertHadPrimarySource(Object id, Object id2, Object id1,
			Object pe, Object q2, Object q1, Object dAttrs) {
		
		HadPrimarySource hadPrimarySource = new HadPrimarySource();
		
		Entity generatedEntity = entities.get((String) id2);
		Entity usedEntity = entities.get((String) id1);
		Activity activity = activities.get((String) pe);
		WasGeneratedBy wasGeneratedBy = (WasGeneratedBy) relations.get((String) q2);
		Used used = (Used) relations.get((String) q1);
		
		generatedEntity.addRelation(hadPrimarySource);
		
		hadPrimarySource.setId((String) id);
		hadPrimarySource.setGeneratedEntity(generatedEntity);
		hadPrimarySource.setUsedEntity(usedEntity);
		hadPrimarySource.setActivity(activity);
		hadPrimarySource.setGeneration(wasGeneratedBy);
		hadPrimarySource.setUsage(used);
		
		String v = "" + dAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					hadPrimarySource.addLabel(value);
				} else if (key.equals("prov:type")) {
					hadPrimarySource.addType(value);
				} else {
					hadPrimarySource.addValue(key, value);
				}
			}
			break;
		}

		if (hadPrimarySource.getId() != null) {
			relations.put(hadPrimarySource.getId(), hadPrimarySource);
		}
		return hadPrimarySource;
	}
	
	// Component 3

	@Override
	public Object convertAgent(Object id, Object attrs) {
		
		Agent agent = new Agent();
		agent.setId((String) id);
		String v = "" + attrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					agent.addLabel(value);
				} else if (key.equals("prov:type")) {
					agent.addType(value);
				} else if (key.equals("prov:location")) {
					agent.addLocation(value);
				} else {
					agent.addValue(key, value);
				}
			}
			break;
		}

		agents.put(agent.getId(), agent);
		return agent;
	}

	@Override
	public Object convertWasAttributedTo(Object id, Object id2, Object id1,
			Object aAttrs) {
		
		WasAttributedTo wasAttributedTo = new WasAttributedTo();
		
		Entity entity = entities.get((String) id2);
		Agent agent = agents.get((String) id1);
		
		entity.addRelation(wasAttributedTo);
		
		wasAttributedTo.setId((String) id);
		wasAttributedTo.setEntity(entity);
		wasAttributedTo.setAgent(agent);
		
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasAttributedTo.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasAttributedTo.addType(value);
				} else {
					wasAttributedTo.addValue(key, value);
				}
			}
			break;
		}

		if (wasAttributedTo.getId() != null) {
			relations.put(wasAttributedTo.getId(), wasAttributedTo);
		}
		return wasAttributedTo;
	}

	@Override
	public Object convertWasAssociatedWith(Object id, Object id2, Object id1,
			Object pl, Object aAttrs) {
		
		WasAssociatedWith wasAssociatedWith = new WasAssociatedWith();
		
		Activity activity = activities.get((String) id2);
		Agent agent = agents.get((String) id1);
		Entity plan = entities.get((String) pl);
		
		activity.addRelation(wasAssociatedWith);
		
		wasAssociatedWith.setId((String) id);
		wasAssociatedWith.setActivity(activity);
		wasAssociatedWith.setAgent(agent);
		wasAssociatedWith.setPlan(plan);
		
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasAssociatedWith.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasAssociatedWith.addType(value);
				} else if (key.equals("prov:role")) {
					wasAssociatedWith.addRole(value);
				} else {
					wasAssociatedWith.addValue(key, value);
				}
			}
			break;
		}

		if (wasAssociatedWith.getId() != null) {
			relations.put(wasAssociatedWith.getId(), wasAssociatedWith);
		}
		return wasAssociatedWith;
	}

	@Override
	public Object convertActedOnBehalfOf(Object id, Object id2, Object id1,
			Object a, Object aAttrs) {
		
		ActedOnBehalfOf actedOnBehalfOf = new ActedOnBehalfOf();
		
		Agent subordinate = agents.get((String) id2);
		Agent responsible = agents.get((String) id1);
		Activity activity = activities.get((String) a);
		
		subordinate.addRelation(actedOnBehalfOf);
		
		actedOnBehalfOf.setId((String) id);
		actedOnBehalfOf.setSubordinate(subordinate);
		actedOnBehalfOf.setResponsible(responsible);
		actedOnBehalfOf.setActivity(activity);
		
		String v = "" + aAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					actedOnBehalfOf.addLabel(value);
				} else if (key.equals("prov:type")) {
					actedOnBehalfOf.addType(value);
				} else {
					actedOnBehalfOf.addValue(key, value);
				}
			}
			break;
		}

		if (actedOnBehalfOf.getId() != null) {
			relations.put(actedOnBehalfOf.getId(), actedOnBehalfOf);
		}
		return actedOnBehalfOf;
	}

	@Override
	public Object convertWasInfluencedBy(Object id, Object id2, Object id1,
			Object dAttrs) {

		WasInfluencedBy wasInfluencedBy = new WasInfluencedBy();
		
		Agent influencee = agents.get((String) id2);
		Agent influencer = agents.get((String) id1);
		
		influencee.addRelation(wasInfluencedBy);
		
		wasInfluencedBy.setId((String) id);
		wasInfluencedBy.setInfluencee(influencee);
		wasInfluencedBy.setInfluencer(influencer);
		
		String v = "" + dAttrs + "";
		while (!v.isEmpty()) {
			String[] array = v.split("=|,");
			List<String> wordList = Arrays.asList(array);
			int i = array.length;
			int k = 0;

			// Get each key-value pair
			for (k = 0; k < i; k = k + 2) {

				String key = wordList.get(k);
				String value = unwrap(wordList.get(k + 1));

				if (key.equals("prov:label")) {
					wasInfluencedBy.addLabel(value);
				} else if (key.equals("prov:type")) {
					wasInfluencedBy.addType(value);
				} else {
					wasInfluencedBy.addValue(key, value);
				}
			}
			break;
		}

		if (wasInfluencedBy.getId() != null) {
			relations.put(wasInfluencedBy.getId(), wasInfluencedBy);
		}
		return wasInfluencedBy;
	}
	
	// Component 4 found below in bundles
	
	// Component 5

	@Override
	public Object convertAlternateOf(Object id2, Object id1) {

		AlternateOf alternateOf = new AlternateOf();
		
		Entity alternate1 = entities.get((String) id2);
		Entity alternate2 = entities.get((String) id1);
		
		alternate1.addRelation(alternateOf);
		
		alternateOf.setAlternate1(alternate1);
		alternateOf.setAlternate2(alternate2);
		
		return alternateOf;
	}

	@Override
	public Object convertSpecializationOf(Object id2, Object id1) {
		
		SpecializationOf specializationOf = new SpecializationOf();
		
		Entity specializedEntity = entities.get((String) id2);
		Entity generalEntity = entities.get((String) id1);
		
		specializedEntity.addRelation(specializationOf);
		
		specializationOf.setSpecializedEntity(specializedEntity);
		specializationOf.setGeneralEntity(generalEntity);
		
		return specializationOf;
	}

	@Override
	public Object convertMentionOf(Object su, Object bu, Object ta) {

		MentionOf mentionOf = new MentionOf();
		
		Entity specializedEntity = entities.get((String) su);
		Entity generalEntity = entities.get((String) bu);
		Entity entity = entities.get((String) ta);
		
		specializedEntity.addRelation(mentionOf);
		
		mentionOf.setSpecializedEntity(specializedEntity);
		mentionOf.setGeneralEntity(generalEntity);
		mentionOf.setBundle(entity);
		
		return mentionOf;
	}
	
	// Component 6

	@Override
	public Object convertInsertion(Object id, Object id2, Object id1,
			Object map, Object dAttrs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertRemoval(Object id, Object id2, Object id1,
			Object keyset, Object dAttrs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertEntry(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertKeyEntitySet(List<Object> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertKeys(List<Object> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertCollectionMemberOf(Object id, Object id2, Object map,
			Object complete, Object dAttrs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertDictionaryMemberOf(Object id, Object id2, Object map,
			Object complete, Object dAttrs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertExtension(Object name, Object id, Object args,
			Object dAttrs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertNamedBundle(Object id, Object nss, List<Object> records) {
		
		Bundle bundle = new Bundle();
		bundle.setId((String) id);
		
		for (Object o : records) {
			bundle.addRecord((Record) o);
		}

		return bundle;
	}

	@Override
	public Object convertAttributes(List<Object> attributes) {
		String s = "";
		boolean first = true;
		for (Object o : attributes) {
			if (first) {
				first = false;
				s = s + o;
			} else {
				s = s + "," + o;
			}
		}
		return s;
	}

	@Override
	public Object convertId(String id) {
		return id;
	}

	@Override
	public Object convertAttribute(Object name, Object value) {
		return name + "=" + value;
	}

	@Override
	public Object convertString(String s) {
		return s;
	}

	@Override
	public Object convertString(String s, String lang) {
		return s;
	}

	@Override
	public Object convertInt(int i) {
		return i;
	}

	@Override
	public Object convertQualifiedName(String qname) {
		return qname;
	}

	@Override
	public Object convertIRI(String iri) {
		return iri;
	}

	@Override
	public Object convertPrefix(String pre) {
		return pre;
	}

	@Override
	public Object convertTypedLiteral(String datatype, Object value) {
		return value;
	}

	@Override
	public Object convertNamespace(Object pre, Object iri) {
		return iri;
	}

	@Override
	public Object convertDefaultNamespace(Object iri) {
		return iri;
	}

	@Override
	public Object convertNamespaces(List<Object> namespaces) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convertBundle(Object nss, List<Object> records,
			List<Object> bundles) {
		Bundle bundle = new Bundle();
		for (Object o : records) {
			bundle.addRecord((Record) o);
		}

		for (Object o : bundles) {
			bundle.addBundle((Bundle) o);
		}
		return bundle;
	}

	public String unwrap(String s) {
		if (s.length() > 1 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
			return s.substring(1, s.length() - 1);
		} else {
			return s;
		}
	}

}
