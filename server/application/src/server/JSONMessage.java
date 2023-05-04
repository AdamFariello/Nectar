package server;
import org.json.*;

public class JSONMessage {
	public String message;
	public JSONObject data;
	
	public JSONMessage(String message, String data){
		//System.out.println("making object");
		this.message = message;
		//System.out.println("making data obj: " + data);
		this.data = new JSONObject(data);
		//System.out.println(this.data);
	}
	
	public String encode() {
		JSONObject obj = new JSONObject();
		obj.put("message", message);
		obj.put("data", data);
		return obj.toString();
	}
	
}
