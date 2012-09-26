package org.openprovenance.prov.java.component1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Entity class
 */
public class EntityTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EntityTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( EntityTest.class );
    }

    /**
     * Test simple creation of Entity
     */
    public void testEntityIdentity()
    {
        Entity entity = new Entity();
        entity.setId("ex:e1");
        
        assertEquals(entity.getId(), "ex:e1");
    }
    
    /**
     * Test simple creation of Entity with attributes
     */
    public void testEntityAttributes()
    {
        Entity entity = new Entity();
        entity.setId("ex:e1");
        entity.addLabel("Axe Head");
        entity.addType("Artifact");
        entity.addLocation("Mountsandel");
        entity.addValue("ex:era", "Neolithic");
        
        assertEquals(entity.getId(), "ex:e1");
        assertTrue(entity.getLabels().contains("Axe Head"));
        assertTrue(entity.getTypes().contains("Artifact"));
        assertTrue(entity.getLocations().contains("Mountsandel"));
        assertTrue(entity.getValues().containsKey("ex:era") && entity.getValue("ex:era").equals("Neolithic"));
        
        entity.removeValue("ex:era");
        
        assertFalse(entity.getValues().containsKey("ex:era"));
    }
}
