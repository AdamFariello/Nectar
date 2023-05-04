package server;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import bl.ValueObject;

public class JsonEncoder implements Encoder.Text<ValueObject>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(ValueObject arg0) throws EncodeException {
		// TODO Auto-generated method stub
		return null;
	}

}
