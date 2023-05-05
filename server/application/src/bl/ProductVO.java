package bl;

public class ProductVO extends ValueObject{
	public String title;
    public String url;
    public String siteName;
	@Override
	protected String[] getEqualityComponents() {
		String[] s = {title, url, siteName};
        return s;   
	}
}
