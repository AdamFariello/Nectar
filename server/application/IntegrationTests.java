import databaseCode;

public class IntegrationTests {
    public void testNotifyUserOnProductChange(){
        ProductTracker tracker = new ProductTracker();
        //add user with user id to db
        //databaseCode.DBInsert.insertIntoTable()
        tracker.addUser("1", "1", "testurl", "Stub");
        tracker.trackProducts();
        tracker.trackProducts();
        
    }
}
