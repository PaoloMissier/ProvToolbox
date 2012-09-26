package org.openprovenance.prov.java;

import java.util.LinkedList;
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
import org.openprovenance.prov.java.component2.WasDerivedFrom;
import org.openprovenance.prov.java.component3.ActedOnBehalfOf;
import org.openprovenance.prov.java.component3.Agent;
import org.openprovenance.prov.java.component3.WasAssociatedWith;
import org.openprovenance.prov.java.component3.WasAttributedTo;
import org.openprovenance.prov.java.component3.WasInfluencedBy;
import org.openprovenance.prov.java.component4.Bundle;
import org.openprovenance.prov.java.component5.AlternateOf;
import org.openprovenance.prov.java.component5.MentionOf;
import org.openprovenance.prov.java.component5.SpecializationOf;

public class JProvTraversal {

	private JProvTreeConstructor jtc;

	public JProvTraversal(JProvTreeConstructor jtc) {
		this.jtc = jtc;
	}

	public Object convert(Bundle b) {
		List<Object> lnkRecords = new LinkedList<Object>();
		List<Object> aRecords = new LinkedList<Object>();
		List<Object> eRecords = new LinkedList<Object>();
		List<Object> agRecords = new LinkedList<Object>();
		List<Object> bRecords = new LinkedList<Object>();

		for (Element e : b.getRecords().getElements()) {
			if (e instanceof Entity) {
				eRecords.add(convert((Entity) e));
			} else if (e instanceof Activity) {
				aRecords.add(convert((Activity) e));
			} else if (e instanceof Agent) {
				agRecords.add(convert((Agent) e));
			}
		}

		for (Relation relation : b.getRecords().getRelations()) {
			Object o = convertRelation(relation);
			if (o != null)
				lnkRecords.add(o);
		}

		for (Bundle bu : b.getBundles()) {
			Object o = convert(bu);
			if (o != null)
				bRecords.add(o);
		}

		if (b.getId() == null) {
			return jtc.convertBundle(null, aRecords,
					eRecords, agRecords, lnkRecords, bRecords);
		} else {
			return jtc.convertNamedBundle(b.getId(),
					null, aRecords, eRecords,
					agRecords, lnkRecords);
		}
	}

	public Object convert(Entity e) {
		List<Object> tAttrs = convertTypeAttributes(e);
		List<Object> lAttrs = convertLabelAttributes(e);
		List<Object> otherAttrs = convertAttributes(e);
		otherAttrs.addAll(convertLocationAttributes(e));

		return jtc.convertEntity(jtc.convert(e.getId()), tAttrs, lAttrs,
				otherAttrs);
	}

	public Object convert(Activity e) {
		List<Object> tAttrs = convertTypeAttributes(e);
		List<Object> lAttrs = convertLabelAttributes(e);
		List<Object> otherAttrs = convertAttributes(e);
		otherAttrs.addAll(convertLocationAttributes(e));

		return jtc.convertActivity(jtc.convert(e.getId()), tAttrs, lAttrs,
				otherAttrs, e.getStartTime(), e.getEndTime());
	}

	public Object convert(Agent e) {
		List<Object> tAttrs = convertTypeAttributes(e);
		List<Object> lAttrs = convertLabelAttributes(e);
		List<Object> otherAttrs = convertAttributes(e);
		otherAttrs.addAll(convertLocationAttributes(e));
		
		return jtc.convertAgent(jtc.convert(e.getId()), tAttrs, lAttrs,
				otherAttrs);
	}

	public List<Object> convertTypeAttributes(HasType e) {
		List<Object> attrs = new LinkedList<Object>();
		for (Object type : e.getTypes()) {
			attrs.add(quoteWrap(type));
		}
		return attrs;
	}

	public List<Object> convertLabelAttributes(HasLabel e) {
		List<Object> res = new LinkedList<Object>();
		for (Object label : e.getLabels()) {
			res.add(quoteWrap(label));
		}
		return res;
	}

	public List<Object> convertRoleAttributes(HasRole e) {
		List<Object> res = new LinkedList<Object>();
		for (Object role : e.getRoles()) {
			res.add(jtc.convertAttribute("prov:role", quoteWrap(role)));
		}
		return res;
	}

	public List<Object> convertLocationAttributes(HasLocation e) {
		List<Object> res = new LinkedList<Object>();
		for (Object location : e.getLocations()) {
			res.add(jtc.convertAttribute("prov:location", quoteWrap(location)));
		}
		return res;
	}

	public List<Object> convertAttributes(HasAny e) {
		List<Object> attrs = new LinkedList<Object>();
		for (Map.Entry<String, String> entry : e.getValues().entrySet()) {
			attrs.add(jtc.convertAttribute(entry.getKey(), quoteWrap(entry.getValue())));
		}
		return attrs;
	}

	public Object convertRelation(Relation r) {

		if (r instanceof Used) {
			return convert((Used) r);
		} else if (r instanceof WasInformedBy) {
			return convert((WasInformedBy) r);
		} else if (r instanceof WasInfluencedBy) {
			return convert((WasInfluencedBy) r);
		} else if (r instanceof WasGeneratedBy) {
			return convert((WasGeneratedBy) r);
		} else if (r instanceof WasInvalidatedBy) {
			return convert((WasInvalidatedBy) r);
		} else if (r instanceof WasStartedBy) {
			return convert((WasStartedBy) r);
		} else if (r instanceof WasEndedBy) {
			return convert((WasEndedBy) r);

		} else if (r instanceof WasDerivedFrom) {
			return convert((WasDerivedFrom) r);

		} else if (r instanceof WasAttributedTo) {
			return convert((WasAttributedTo) r);
		} else if (r instanceof WasAssociatedWith) {
			return convert((WasAssociatedWith) r);
		} else if (r instanceof ActedOnBehalfOf) {
			return convert((ActedOnBehalfOf) r);

		} else if (r instanceof AlternateOf) {
			return convert((AlternateOf) r);
		} else if (r instanceof SpecializationOf) {
			return convert((SpecializationOf) r);
		} else if (r instanceof MentionOf) {
			return convert((MentionOf) r);

		} else {
			throw new UnsupportedOperationException("Unknown relation type "
					+ r);
		}
	}

	public Object convert(Used o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));
		otherAttrs.addAll(convertRoleAttributes((HasRole) o));
		return jtc.convertUsed(o.getId(), tAttrs, otherAttrs, o.getActivity(),
				o.getEntity(), o.getTime());
	}

	public Object convert(WasGeneratedBy o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));

		return jtc.convertWasGeneratedBy(o.getId(), tAttrs, otherAttrs,
				o.getEntity(), o.getActivity(), o.getTime());
	}

	public Object convert(WasStartedBy o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));
		return jtc.convertWasStartedBy(o.getId(), tAttrs, otherAttrs,
				o.getActivity(), o.getTrigger(), o.getStarter(), o.getTime());
	}

	public Object convert(WasEndedBy o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));
		return jtc.convertWasEndedBy(o.getId(), tAttrs, otherAttrs,
				o.getActivity(), o.getTrigger(), o.getEnder(), o.getTime());
	}

	public Object convert(WasInvalidatedBy o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));

		return jtc.convertWasInvalidatedBy(o.getId(), tAttrs, otherAttrs,
				o.getEntity(), o.getActivity(), o.getTime());
	}

	public Object convert(WasInformedBy o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));

		return jtc.convertWasInformedBy(o.getId(), tAttrs, otherAttrs,
				o.getEffect(), o.getCause());
	}

	// Component 2
	
	public Object convert(WasDerivedFrom o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		return jtc.convertWasDerivedFrom(o.getId(), tAttrs, otherAttrs,
				o.getGeneratedEntity(), o.getUsedEntity(), o.getActivity(),
				o.getGeneration(), o.getUsage());
	}
	
	// Component 3

	public Object convert(WasInfluencedBy o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));

		return jtc.convertWasInfluencedBy(o.getId(), tAttrs, otherAttrs,
				o.getInfluencee(), o.getInfluencer());
	}

	public Object convert(WasAssociatedWith o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));
		otherAttrs.addAll(convertRoleAttributes((HasRole) o));

		return jtc.convertWasAssociatedWith(o.getId(), tAttrs, otherAttrs,
				o.getActivity(), o.getAgent(), o.getPlan());
	}

	public Object convert(WasAttributedTo o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));

		return jtc.convertWasAttributedTo(o.getId(), tAttrs, otherAttrs,
				o.getEntity(), o.getAgent());
	}

	public Object convert(ActedOnBehalfOf o) {
		List<Object> tAttrs = convertTypeAttributes((HasType) o);
		List<Object> otherAttrs = convertAttributes((HasAny) o);
		otherAttrs.addAll(convertLabelAttributes((HasLabel) o));

		return jtc.convertActedOnBehalfOf(o.getId(), tAttrs, otherAttrs,
				o.getSubordinate(), o.getResponsible(), o.getActivity());
	}

	public Object convert(AlternateOf o) {

		return jtc.convertAlternateOf(o.getAlternate1(), o.getAlternate2());
	}

	public Object convert(SpecializationOf o) {
		return jtc.convertSpecializationOf(o.getSpecializedEntity(),
				o.getGeneralEntity());
	}

	public Object convert(MentionOf o) {
		return jtc.convertMentionOf(o.getSpecializedEntity(),
				o.getGeneralEntity(), o.getBundle());
	}

	public String quoteWrap(Object b) {
		return "\"" + b + "\"";
	}
}
