package org.openprovenance.prov.notation;

import org.openprovenance.prov.xml.Attribute;
import org.openprovenance.prov.xml.BeanConstructor;
import org.openprovenance.prov.xml.ProvFactory;

import static org.openprovenance.prov.xml.NamespacePrefixMapper.XSI_NS;

import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;

/** A class that implements the BeanConstructor interface and relies on a TreeConstructor to construct a data structure for a given bean. */
public class BeanTreeConstructor implements BeanConstructor{
    private TreeConstructor c;
    final ProvFactory pFactory;
    public BeanTreeConstructor(ProvFactory pFactory, TreeConstructor c) {
        this.pFactory=pFactory;
        this.c=c;
        this.namespaces=pFactory.getNss();
    }

    
    
    public Object convert(QName q) {
        if (q==null) return null;
        registerPrefix(q);
        if (q.getPrefix()!=null) {
            return q.getPrefix()+ ":" + q.getLocalPart();
        } else { 
            return q.getLocalPart();
        }
    }

    final Hashtable<String,String> namespaces;
    
    public void registerPrefix(QName q) {
        //System.out.println("registeringPrefix " + q);
        String prefix=q.getPrefix();
        if (prefix==null) {
            namespaces.put("_", q.getNamespaceURI());
        } else {
           String old=namespaces.put(q.getPrefix(), q.getNamespaceURI());
           //if (old==null) System.out.println("registeringPrefix " + q.getPrefix() + " " + q.getNamespaceURI());
        }
    }
    


    public Object convertAttributeValue(Element a) {
        String type=a.getAttributeNS(XSI_NS,"type");
        if ((type==null) || ("".equals(type))) {
            //System.out.println("----> convertAttributeValue " + type + " " + a.getFirstChild().getNodeValue());
            return a.getFirstChild().getNodeValue();
        } else {
            //System.out.println("----> convertAttributeValue " + type);
            return c.convertTypedLiteral(type,
                                         "\"" + a.getFirstChild().getNodeValue() + "\"");
        }
    }

    public Object convertAttribute(Object name, Object value) {
        return c.convertAttribute(name,value);
    }





    public Object convertTypedLiteral(String datatype, Object value) {
        return c.convertTypedLiteral(datatype,value);
    }

    public List<Object> convertTypeAttributes(List<Object> tAttrs) {
        List<Object> attrs=new LinkedList<Object>();
        for (Object a: tAttrs) {
            attrs.add(c.convertAttribute("prov:type",a));
        }
        return attrs;
    }

    public List<Object> convertLabelAttributes(List<Object> lAttrs) {
        List<Object> attrs=new LinkedList<Object>();
        for (Object a: lAttrs) {
            attrs.add(c.convertAttribute("prov:label",a));
        }
        return attrs;
    }
    public List<Object> convertLocationAttributes(List<Object> lAttrs) {
        List<Object> attrs=new LinkedList<Object>();
        for (Object a: lAttrs) {
            attrs.add(c.convertAttribute("prov:location",a));
        }
        return attrs;
    }
    public List<Object> convertRoleAttributes(List<Object> lAttrs) {
        List<Object> attrs=new LinkedList<Object>();
        for (Object a: lAttrs) {
            attrs.add(c.convertAttribute("prov:role",a));
        }
        return attrs;
    }
    public Object convertValueAttribute(Object value) {
        if (value==null) return null;
        return c.convertAttribute("prov:value",value);

    }



    public Object convertEntity(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Object> locAttrs, Object value, List<Attribute> otherAttrs) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);
        List<?> locAttrs2=convertLocationAttributes(locAttrs);
        Object value2=convertValueAttribute(value);
        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(locAttrs2);
        if (value2!=null) attrs.add(value2);
        attrs.addAll(otherAttrs);

        return c.convertEntity(id,
                               c.convertAttributes(attrs));
    }

    public Object convertActivity(Object id, List<Object> tAttrs, List<Object> lAttrs,  List<Object> locAttrs, List<Attribute> otherAttrs, Object startTime, Object endTime) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);
        List<?> locAttrs2=convertLocationAttributes(locAttrs);
        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(lAttrs2);
        attrs.addAll(tAttrs2);
        attrs.addAll(locAttrs2);
        attrs.addAll(otherAttrs);

        return c.convertActivity(id,
                                 startTime, 
                                 endTime, 
                                 c.convertAttributes(attrs));
    }


    public Object convertAgent(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Object> locAttrs, List<Attribute> otherAttrs) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);
        List<?> locAttrs2=convertLocationAttributes(locAttrs);
        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(lAttrs2);
        attrs.addAll(tAttrs2);
        attrs.addAll(locAttrs2);
        attrs.addAll(otherAttrs);

        return c.convertAgent(id,
                              c.convertAttributes(attrs));
    }



    public Object convertUsed(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Object> locAttrs, List<Object> roleAttrs, List<Attribute> otherAttrs, Object activity, Object entity, Object time) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);
        List<?> locAttrs2=convertLocationAttributes(locAttrs);
        List<?> roleAttrs2=convertRoleAttributes(roleAttrs);

        //List otherAttrs2=convertAttributes(otherAttrs);
        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(locAttrs2);
        attrs.addAll(roleAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertUsed(id,
                             activity,
                             entity,
                             time,
                             c.convertAttributes(attrs));
    }

    public Object convertWasGeneratedBy(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Object> locAttrs, List<Object> roleAttrs, List<Attribute> otherAttrs, Object entity, Object activity, Object time) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);
        List<?> locAttrs2=convertLocationAttributes(locAttrs);
        List<?> roleAttrs2=convertRoleAttributes(roleAttrs);

        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(locAttrs2);
        attrs.addAll(roleAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasGeneratedBy(id,
                                       entity,
                                       activity,
                                       time,
                                       c.convertAttributes(attrs));
    }

    public Object convertWasInvalidatedBy(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Object> locAttrs, List<Object> roleAttrs, List<Attribute> otherAttrs, Object entity, Object activity, Object time) {
	List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);
        List<?> locAttrs2=convertLocationAttributes(locAttrs);
        List<?> roleAttrs2=convertRoleAttributes(roleAttrs);

        
        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(locAttrs2);
        attrs.addAll(roleAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasInvalidatedBy(id,
                                         entity,
                                         activity,
                                         time,
                                         c.convertAttributes(attrs));
    }

    public Object convertWasStartedBy(Object id, List<Object> tAttrs, List<Object> lAttr, List<Object> locAttr, List<Object> roleAttr, List<Attribute> otherAttrs, Object activity, Object entity, Object starter, Object time) {
	List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttr);
        List<?> locAttrs2=convertLocationAttributes(locAttr);
        List<?> roleAttrs2=convertRoleAttributes(roleAttr);

	
        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(locAttrs2);
        attrs.addAll(roleAttrs2);
        attrs.addAll(otherAttrs);
        
        return c.convertWasStartedBy(id,
				     activity,
				     entity,
				     starter,
				     time,
				     c.convertAttributes(attrs));
    }

    public Object convertWasEndedBy(Object id, List<Object> tAttrs, List<Object> lAttr, List<Object> locAttr, List<Object> roleAttr, List<Attribute> otherAttrs, Object activity, Object entity, Object ender, Object time) {
	List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttr);
        List<?> locAttrs2=convertLocationAttributes(locAttr);
        List<?> roleAttrs2=convertRoleAttributes(roleAttr);

        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(locAttrs2);
        attrs.addAll(roleAttrs2);
        attrs.addAll(otherAttrs);
        
        return c.convertWasEndedBy(id,
				   activity,
				   entity,
				   ender,
				   time,
				   c.convertAttributes(attrs));
    }

    
    public Object convertWasInformedBy(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Attribute> otherAttrs, Object effect, Object cause) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);

        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasInformedBy(id,
				      effect,
				      cause,
				      c.convertAttributes(attrs));
    }

    public Object convertWasDerivedFrom(Object id, List<Object> tAttrs, List<Object> lAttrs,  List<Attribute> otherAttrs, 
                                        Object effect, Object cause,
                                        Object activity, Object generation, Object usage) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);

        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasDerivedFrom(id,
                                       effect,
                                       cause,
                                       activity,
                                       generation,
                                       usage,
                                       c.convertAttributes(attrs));
    }

    public Object convertWasAssociatedWith(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Object> rAttrs, List<Attribute> otherAttrs, Object activity, Object agent, Object plan) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);
        List<?> roleAttrs2=convertRoleAttributes(rAttrs);

        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(roleAttrs2);
        attrs.addAll(otherAttrs);
        
      
        return c.convertWasAssociatedWith(id,
                                          activity,
                                          agent,
                                          plan,
                                          c.convertAttributes(attrs));
    }

    public Object convertWasAttributedTo(Object id, List<Object> tAttrs,  List<Object> lAttrs, List<Attribute> otherAttrs, Object entity, Object agent) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);


        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);
        attrs.addAll(otherAttrs);
        return c.convertWasAttributedTo(id,
					entity,
					agent,
					c.convertAttributes(attrs));
    }

    public Object convertActedOnBehalfOf(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Attribute> otherAttrs, Object subordinate, Object responsible, Object activity) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);


        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);

        attrs.addAll(otherAttrs);
        return c.convertActedOnBehalfOf(id,
					subordinate,
					responsible,
					activity,
					c.convertAttributes(attrs));
    }

    public Object convertWasInfluencedBy(Object id, List<Object> tAttrs, List<Object> lAttrs, List<Attribute> otherAttrs, Object effect, Object cause) {
        List<?> tAttrs2=convertTypeAttributes(tAttrs);
        List<?> lAttrs2=convertLabelAttributes(lAttrs);


        List<Object> attrs=new LinkedList<Object>();
        attrs.addAll(tAttrs2);
        attrs.addAll(lAttrs2);

        attrs.addAll(otherAttrs);
        return c.convertWasInfluencedBy(id,
					effect,
					cause,
					c.convertAttributes(attrs));
    }


    public Object convertAlternateOf(Object entity2, Object entity1) {
        return c.convertAlternateOf(entity2,
                                    entity1);
    }

    public Object convertSpecializationOf(Object entity2, Object entity1) {
        return c.convertSpecializationOf(entity2,
                                         entity1);
    }


    public Object convertMentionOf(Object entity2, Object entity1, Object bundle) {
        return c.convertMentionOf(entity2,
				  entity1,
				  bundle);
    }


    
    public Object convertHadMember(Object collection, Object entity) {
	return c.convertHadMember(collection,entity);
    }



    public Object convertBundle(Object namespaces,
				List<Object> sRecords,
				List<Object> bRecords) {
        //System.out.println("namespaces in BeanTreeConstructor " + namespaces);
        if (namespaces==null) {
            namespaces=this.namespaces;
        } else {
            Hashtable<String,String> nss=(Hashtable<String,String>) namespaces;
            nss.putAll(this.namespaces);
        }
        
        //System.out.println("namespaces in BeanTreeConstructor  " + namespaces);

        return c.convertDocument(namespaces,sRecords,bRecords);
    }


    public Object convertNamedBundle(Object id,
                                     Object namespaces,
				     List<Object> aRecords,
                                     List<Object> eRecords,
                                     List<Object> agRecords,
                                     List<Object> lnkRecords) {
        List<Object> ll=new LinkedList<Object>();
        if (aRecords!=null) ll.addAll(aRecords);
        if (eRecords!=null) ll.addAll(eRecords);
        if (agRecords!=null) ll.addAll(agRecords);
        if (lnkRecords!=null) ll.addAll(lnkRecords);
        return c.convertNamedBundle(id,namespaces,ll);
    }


//	public Object convertEntity(Object id, List<Object> tAttrs, Object lAttr,
//			List<Object> otherAttrs) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object convertAgent(Object id, List<Object> tAttrs, Object lAttr,
//			List<Object> otherAttrs) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object convertActivity(Object id, List<Object> tAttrs, Object lAttr,
//			List<Object> otherAttrs, Object startTime, Object endTime) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
