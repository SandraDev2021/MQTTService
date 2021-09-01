package mqttService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

//import mqqt.Broker;

public class MqttSubscriber {
	
	public static void main(String[] args) {
		 //declare the broker and the client Id
		 String broker = "tcp://localhost:1883";
		 String clientId = "Subscriber";
		
		 //create an object for persistence
		MemoryPersistence persistence = new MemoryPersistence();

			try {
				//create a new object for the client
				MqttClient client = new MqttClient(broker,clientId,persistence);
				
				MqttConnectOptions opts = new MqttConnectOptions();//create an object for options
				
				opts.setCleanSession(false);//necessary. If you want to clean the session set true
				// if you want to retain the messages, set false
				
				client.setCallback(new Broker());// set the callback using the class' name which encapsulates it : Broker
				// this is specific for the subscriber
				
				System.out.println("Connecting  subscriber to broker :" + broker);
				client.connect(opts);// connecting the subscriber parsing the options opts
				System.out.println("connected");
				
				// subscribe to a topic
				//client.subscribe("/Course/ReferenceLetter/Static");//
				//client.subscribe("/Course/Translator");
				client.subscribe(new String[] {"/Course/ReferenceLetter/Static","/Course/Translator"}, new int[] {2,2});
		           
	            // if you want to subscribe to multiple topics at the same time, and provide QoS levels:
	            //client.subscribe(new String[]{"myTopic","Fruit/#"}, new int[]{2,1});// you set arrays
	            
	            //to stop receiving messages, unsubscribe:
	            //client.unsubscribe(new String[]{"myTopic","Fruit/#"});
	            
	            //client.disconnect();
				
				
				
			} catch (MqttException ex) {
				System.out.println("Message:" + ex.getMessage());
				System.out.println("Reason:" + ex.getReasonCode());
				System.out.println("Cause" + ex.getCause());
				ex.printStackTrace();
			}

		}// end of main method

}// end of Class MqttSubscriber
