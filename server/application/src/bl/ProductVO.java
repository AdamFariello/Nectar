package bl;

import org.json.simple.JSONObject;

public class ProductVO extends JSONEncodeableValueObject{
	public String title;
    public String url;
    public String siteName;
	@Override
	protected String[] getEqualityComponents() {
		String[] s = {title, url, siteName};
        return s;   
	}
	@Override
	protected String encode() {
		JSONObject obj = new JSONObject();
		obj.put("title", title);
		obj.put("siteName", siteName);
		obj.put("url", url);
		return obj.toString();
	}
	
	
}
