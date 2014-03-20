import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class IntruderAlarm {
	static InputStream  input;

	static String from = "sir.robot4003@gmail.com";//sender email
	static String password = "sirrobot4003";//sender password
	static String[] to = { "callum.miller70@gmail.com", "jennysayshi@hotmail.co.uk", "walleyer@hotmail.co.uk"}; // list of recipient email addresses
	static String host="smtp.gmail.com";//host
	static String portformail="465";//port
	
	//This function will read value from serial port and send email if the value is less than 50
	 	public static void readFromArduino() throws Exception {
	 
	 		// for windows
	 		CommPortIdentifier portId = CommPortIdentifier
	 				.getPortIdentifier("COM3");//serial port ID
	 		
	 		SerialPort port = (SerialPort) portId.open("serial talk", 4000);//opens serial port
	 		
	 		input = port.getInputStream();
	 		port.setSerialPortParams(9600, SerialPort.DATABITS_8,
	 				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	 		System.out.println("input " + input);
	 		while (true) {
	 
	 			try {
	 				
	 				if (input.available() > 0) {
	 					
	 					
	 					int value = input.read();
	 
	 					System.out.println(value);
	 					if (value <= 50) { //if value less than or equal 50cm send email
	 						String subject = "INTRUDER ALERT" ;
	 						String body = "INTRUDER DETECTED at *Address*";
	 						sendFromGMail(host, portformail, from, password, to, subject, body);
	 						//message
	 						
	 					}
	 				}
	 			} catch (Exception ex) {
	 				ex.printStackTrace();
	 			}
	 			
	 		
	 		}
	 		
	 		
	 
	 	}
	 
	
	private static void sendFromGMail(String host, String port, String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for( int i = 0; i < to.length; i++ ) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Email sent successfully");

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception{

		readFromArduino();
		//reads the code in Arduino
		
		

	}
	
	
}
