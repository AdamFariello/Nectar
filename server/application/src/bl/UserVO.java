package bl;

public class UserVO extends ValueObject{
	public String userID;
    public String userEmail;
    public String userPassword;
    public String userPhoneNumber;
    
    @Override
	protected String[] getEqualityComponents() {
		String[] s = {userID, userEmail, userPassword, userPhoneNumber};
        return s;   
	}
}
