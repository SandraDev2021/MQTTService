package mqttService;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Broker implements MqttCallback {

	public void connectionLost(Throwable cause) {
		System.out.println("Connection is lost" + cause.getStackTrace());
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(new String(message.getPayload()) + " arrived from topic" + topic + "with qos" + message.getQos());
		//it will publish all the topics available
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		System.out.println("Delivery is complete" + token.isComplete());	
	}

}
