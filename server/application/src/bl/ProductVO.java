package bl;

import org.json.simple.JSONObject;

public class ProductVO extends JSONEncodeableValueObject{
	//TODO public vs private
	public int id;
    public String url;
    public String siteName;
    
    public ProductVO (int id, String url, String siteName) {
    	this.id = id;
        this.url = url;
        this.siteName = siteName;
    }
    
	@Override
	protected String[] getEqualityComponents() {
		String[] s = {Integer.toString(id), url, siteName};
        return s;   
	}
	@Override
	protected String encode() {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("siteName", siteName);
		obj.put("url", url);
		return obj.toString();
	}
	
	
}
