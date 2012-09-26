package org.openprovenance.prov.java.component1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Activity class
 */
public class ActivityTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ActivityTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ActivityTest.class );
    }

    /**
     * Test simple creation of Activity
     */
    public void testActivityIdentity()
    {
        Activity activity = new Activity();
        activity.setId("ex:a1");
        
        assertEquals(activity.getId(), "ex:a1");
    }
    
    /**
     * Test simple creation of Activity with start and end times
     */
    public void testActivityTimed()
    {
        Activity activity = new Activity();
        activity.setId("ex:a1");
        activity.setStartTime("10:00:00");
        activity.setEndTime("11:00:00");
        
        assertEquals(activity.getId(), "ex:a1");
        assertEquals(activity.getStartTime(), "10:00:00");
        assertEquals(activity.getEndTime(), "11:00:00");
    }
    
    /**
     * Test simple creation of Activity with attributes
     */
    public void testActivityAttributes()
    {
        Activity activity = new Activity();
        activity.setId("ex:a1");
        activity.addLabel("Search for artifacts in dig site");
        activity.addType("Excavation");
        activity.addLocation("Mountsandel");
        activity.addValue("ex:era", "Neolithic");
        
        assertEquals(activity.getId(), "ex:a1");
        assertTrue(activity.getLabels().contains("Search for artifacts in dig site"));
        assertTrue(activity.getTypes().contains("Excavation"));
        assertTrue(activity.getLocations().contains("Mountsandel"));
        assertTrue(activity.getValues().containsKey("ex:era") && activity.getValue("ex:era").equals("Neolithic"));
        
        activity.removeValue("ex:era");
        
        assertFalse(activity.getValues().containsKey("ex:era"));
    }
    
    
}
