package mqttService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientCourse {// this is the broker client
	
	private static MqttClient client ;// Variable MqttClient 
	

	public static void main(String[] args) {
		
		String topic = "/Course/ReferenceLetter/Static";// the topic you want to display
		String content = "The Reference Letter is available, please check your email";// message you want to display
		
		int qos = 2;//quality of service 2 (reliable guaranteed and require 4 messages)
		
		String broker = "tcp://localhost:1883"; // define the broker
		String clientId = "Publisher"; // define the clientID which must be unique. The client is the borker's client (not the subscriber)
		
		
		MemoryPersistence persistence = new MemoryPersistence();
		/*
		 * Persistence that uses memory in cases where reliability is not required across client or device
		 * restarts memory this memory persistence can be used. In cases where reliability is required like 
		 * when clean session is set to false then a non-volatile form of persistence should be used. 

		 */
	
		try {
			
			client = new MqttClient(broker, clientId, persistence);//create an instance of the pub_client (a MqttClient)
			MqttConnectOptions opts =  new MqttConnectOptions();// connections options // the method MqttConnectOptions() was auto-generated after the main 
			
			
			/* if cleanSession is true before connecting to the client, then all pending publication deliveries for the client
			 * are removed when the client connects
			 */
			opts.setCleanSession(true);// if you want to retain the message, set false.It won't clean the session
			
			opts.setKeepAliveInterval(180);//miliseconds
			
			//connect
			System.out.println("Connecting client to Broker:" + broker);
			client.connect(opts);// we parse in the options
			System.out.println("connected");
			
			//now, send the messages with their contents
			
			publishMessage(topic,content,2,false);// the method was auto-generated after the main 
		//(String topic, String payload, int qos, boolean retained)
			publishMessage("/Course/ReferenceLetter/Static", "ReferenceLetter available", 1,false);//topic/subtopic1/subtopic 2
			
			//disconnect
			client.disconnect();// disconnect - necessary
			System.out.println("Disconnected");
			client.close();//close the session
			
			System.exit(0);//close the system
			
		}catch(Exception e) {
			System.out.println("Cause"+e.getCause());
			System.out.println("Message"+ e.getMessage());
			System.out.println("Track" +e.getStackTrace());
		}

	}// end of main method
	

	private static void publishMessage(String topic, String payload, int qos, boolean retained) {
		
		System.out.println("Publishing message" + payload +  "on topic:" + topic); // it will display all the publishMessages
		//create a message
		MqttMessage message = new MqttMessage(payload.getBytes());//necessary to create the message
		message.setRetained(retained);// set if the retention will be true or false
		message.setQos(qos);//set it the qos will be 0,1 or 2
		
		try {
			client.publish(topic, message);//publish the topic and the message
			
			
			
			
		}catch(Exception ex) {
			System.out.println("message" + ex.getMessage());
			System.out.println("Cause" + ex.getCause());
			System.out.println("Track" + ex.getStackTrace());
			
		}
		System.out.println("Message published");
	}

}// end of class MqttCLientCourse
