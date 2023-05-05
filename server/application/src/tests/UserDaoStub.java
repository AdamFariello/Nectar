package tests;

import java.util.ArrayList;

import bl.UserDao;

public class UserDaoStub extends UserDao{
	private String[] userIDs;
	private String[] usernames;
	private String[] passwords;
	private String[] productIDs;
	
	public UserDaoStub() {
		userIDs = new String[10];
		usernames = new String[10];
		passwords = new String[10];
		productIDs = new String[10];
		usernames[0] = "john@gmail.com";
		userIDs[0] = "123";
		passwords[0] = "Pass";
		productIDs[0] = "234";
		
	}
	public ArrayList<String> getUserByEmailAddress(String email){
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < usernames.length; i++) {
			if(usernames[i].equals(email)) {
				result.add(email);
				result.add(passwords[i]);	
				return result;
			}
		}
		return null;
		
        
    }
}
