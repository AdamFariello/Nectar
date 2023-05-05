package tests;

import bl.*;

import org.junit.Test;

import junit.framework.*;

public class ProductTrackerIntegrationTests {
    
    @Test
    public void testNotifyUserOnProductChange() throws Exception{
    	
        ProductTracker tracker = new ProductTracker();
        //add user with user id to db
        //databaseCode.DBInsert.insertIntoTable()
        tracker.addUser("1", "1", "testurl", "Stub");
        tracker.trackProducts();
        tracker.trackProducts();
    }
}
