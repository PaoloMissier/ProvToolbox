package org.openprovenance.prov.java;

import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.java.component1.Activity;
import org.openprovenance.prov.java.component1.Entity;
import org.openprovenance.prov.java.component1.Used;
import org.openprovenance.prov.java.component1.WasGeneratedBy;
import org.openprovenance.prov.java.component3.Agent;
import org.openprovenance.prov.notation.TreeConstructor;

public class JProvTreeConstructor implements JProvConstructor {

	private TreeConstructor c;

	public JProvTreeConstructor(TreeConstructor c) {
		this.c = c;
	}

	@Override
	public Object convert(String q) {
		return q;
	}

	public List<Object> convertTypeAttributes(List<Object> tAttrs) {
		List<Object> attrs = new LinkedList<Object>();
		for (Object a : tAttrs) {
			attrs.add(c.convertAttribute("prov:type", a));
		}
		return attrs;
	}

	public List<Object> convertLabelAttributes(List<Object> lAttrs) {
		List<Object> attrs = new LinkedList<Object>();
		for (Object a : lAttrs) {
			attrs.add(c.convertAttribute("prov:label", a));
		}
		return attrs;
	}
	
	public List<Object> convertLocationAttributes(List<Object> lAttrs) {
		List<Object> attrs = new LinkedList<Object>();
		for (Object a : lAttrs) {
			attrs.add(c.convertAttribute("prov:location", a));
		}
		return attrs;
	}
	
	public List<Object> convertRoleAttributes(List<Object> rAttrs) {
		List<Object> attrs = new LinkedList<Object>();
		for (Object a : rAttrs) {
			attrs.add(c.convertAttribute("prov:role", a));
		}
		return attrs;
	}
	
	@Override
	public Object convertAttribute(Object name, Object value) {
		return c.convertAttribute(name, value);
	}

	@Override
	public Object convertTypedLiteral(String datatype, Object value) {
		return c.convertTypedLiteral(datatype, value);
	}

	// Component 1

	@Override
	public Object convertEntity(Object id, List<Object> tAttrs,
			List<Object> lAttr, List<Object> otherAttrs) {

		List<?> tAttrs2 = convertTypeAttributes(tAttrs);
		List<?> lAttrs2 = convertLabelAttributes(lAttr);
		List<Object> attrs = new LinkedList<Object>();
		attrs.addAll(lAttrs2);
		attrs.addAll(tAttrs2);
		attrs.addAll(otherAttrs);

		return c.convertEntity(id, c.convertAttributes(attrs));
	}

	@Override
	public Object convertAgent(Object id, List<Object> tAttrs,
			List<Object> lAttr, List<Object> otherAttrs) {
		
		List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttr);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(lAttrs2);
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);

        return c.convertAgent(id,
                              c.convertAttributes(attrs));
	}

	@Override
	public Object convertActivity(Object id, List<Object> tAttrs,
			List<Object> lAttr, List<Object> otherAttrs, Object startTime,
			Object endTime) {
	
		List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttr);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(lAttrs2);
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);

        return c.convertActivity(id,
                                 startTime, 
                                 endTime, 
                                 c.convertAttributes(attrs));
	}

	@Override
	public Object convertUsed(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Activity activity, Entity entity, Object time) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertUsed(id,
                             activity.getId(),
                             (entity == null) ? null : entity.getId(),
                             time,
                             c.convertAttributes(attrs));
	}

	@Override
	public Object convertWasGeneratedBy(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Entity entity, Activity activity,
			Object time) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasGeneratedBy(id,
                                       entity.getId(),
                                       (activity == null) ? null : activity.getId(),
                                       time,
                                       c.convertAttributes(attrs));
	}

	@Override
	public Object convertWasStartedBy(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Activity activity, Entity entity,
			Activity starter, Object time) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasStartedBy(id,
				     activity.getId(),
				     (entity == null) ? null : entity.getId(),
				     (starter == null) ? null : starter.getId(),
				     time,
				     c.convertAttributes(attrs));
	}

	@Override
	public Object convertWasEndedBy(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Activity activity, Entity entity,
			Activity ender, Object time) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasEndedBy(id,
				   activity.getId(),
				   (entity == null) ? null : entity.getId(),
				   (ender == null) ? null : ender.getId(),
				   time,
				   c.convertAttributes(attrs));
	}

	@Override
	public Object convertWasInvalidatedBy(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Entity entity, Activity activity, Object time) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasInvalidatedBy(id,
                                         entity.getId(),
                                         (activity == null) ? null : activity.getId(),
                                         time,
                                         c.convertAttributes(attrs));
	}

	@Override
	public Object convertWasInformedBy(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Activity effect, Activity cause) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasInformedBy(id,
				      effect.getId(),
				      cause.getId(),
				      c.convertAttributes(attrs));
	}

	// Component 2
	
	@Override
	public Object convertWasDerivedFrom(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Entity generatedEntity, Entity usedEntity, Activity activity,
			WasGeneratedBy generation, Used usage) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasDerivedFrom(id,
                                       generatedEntity.getId(),
                                       usedEntity.getId(),
                                       (activity == null) ? null : activity.getId(),//pe
                                       (generation == null) ? null : generation.getId(),//g2
                                       (usage == null) ?  null : usage.getId(),//u1
                                       c.convertAttributes(attrs));
	}
	
	// Component 3

	@Override
	public Object convertWasInfluencedBy(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Agent influencee, Agent influencer) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasInfluencedBy(id,
					influencee.getId(),
					influencer.getId(),
					c.convertAttributes(attrs));
        
        // TODO: Activity influenced upon
	}

	@Override
	public Object convertWasAssociatedWith(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Activity activity, Agent agent, Entity plan) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasAssociatedWith(id,
                                          activity.getId(),
                                          agent.getId(),
                                          (plan == null) ? null : plan.getId(),
                                          c.convertAttributes(attrs));
	}

	@Override
	public Object convertWasAttributedTo(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Entity entity, Agent agent) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasAttributedTo(id,
					entity.getId(),
					agent.getId(),
					c.convertAttributes(attrs));
	}

	@Override
	public Object convertActedOnBehalfOf(Object id, List<Object> tAttrs,
			List<Object> otherAttrs, Agent subordinate, Agent responsible,
			Activity activity) {
		
		List<Object> tAttrs2=convertTypeAttributes(tAttrs);
        //List<Object> otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs = new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertActedOnBehalfOf(id,
					subordinate.getId(),
					responsible.getId(),
					(activity == null) ? null : activity.getId(),
					c.convertAttributes(attrs));
	}
	
	// Component 5

	@Override
	public Object convertAlternateOf(Entity alternate1, Entity alternate2) {
		
		return c.convertAlternateOf(alternate1.getId(), alternate2.getId());
	}

	@Override
	public Object convertSpecializationOf(Entity specializedEntity, Entity generalEntity) {
		
		return c.convertSpecializationOf(specializedEntity.getId(), generalEntity.getId());
	}

	@Override
	public Object convertMentionOf(Entity specializedEntity, Entity generalEntity, Entity bundle) {
		
		return c.convertMentionOf(specializedEntity.getId(), generalEntity.getId(), bundle.getId());
	}


	@Override
	public Object convertBundle(Object namespaces, List<Object> aRecords,
			List<Object> eRecords, List<Object> agRecords,
			List<Object> lnkRecords, List<Object> bRecords) {
		
		System.out.println("preparing convertBundle");
		
		List<Object> ll = new LinkedList<Object>();
        if (aRecords!=null) ll.addAll(aRecords);
        if (eRecords!=null) ll.addAll(eRecords);
        if (agRecords!=null) ll.addAll(agRecords);
        if (lnkRecords!=null) ll.addAll(lnkRecords);
        
		System.out.println("convertBundle ready to process  "+ll.size()+" objects");

		 long tstart = System.currentTimeMillis();
        Object result = c.convertBundle(namespaces,ll,bRecords);
        long tstop =  System.currentTimeMillis();
        
        System.out.println("time (ms): "+(tstop-tstart));
        return result;
	}

	@Override
	public Object convertNamedBundle(Object id, Object namespaces,
			List<Object> aRecords, List<Object> eRecords,
			List<Object> agRecords, List<Object> lnkRecords) {
		List<Object> ll = new LinkedList<Object>();
        if (aRecords!=null) ll.addAll(aRecords);
        if (eRecords!=null) ll.addAll(eRecords);
        if (agRecords!=null) ll.addAll(agRecords);
        if (lnkRecords!=null) ll.addAll(lnkRecords);
        return c.convertNamedBundle(id,namespaces,ll);
	}

}
