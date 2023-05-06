package bl;

public class UserVO extends ValueObject{
	public String userID;
    public String userEmail;
    public String userPassword;
    public String userPhoneNumber;
    
    public UserVO(String userID, String userEmail, String userPassword, String userPhoneNumber) {
    	this.userID = userID;
    	this.userEmail = userEmail;
    	this.userPassword = userPassword;
    	this.userPhoneNumber = userPhoneNumber;
    }
    
    @Override
	protected String[] getEqualityComponents() {
		String[] s = {userID, userEmail, userPassword, userPhoneNumber};
        return s;   
	}
}
