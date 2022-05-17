package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String msgstring = ((Message)msg ).getMessage();
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}
		else if(msgstring.equalsIgnoreCase("#SignUpWarning"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
		}
		else if(msgstring.equalsIgnoreCase("#MemberSignedUpSucces"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
//			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//			alert.setTitle("SignedUpSuccess");
//			alert.setContentText("Dear client, welcome to Lilach. you have been signed up successfully");
//			alert.showAndWait();
			try {
				App.setRoot("HomePage"); /* change to cataloooooooooooggggggg */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgstring.equalsIgnoreCase("#LogInSucess"))
		{
			System.out.print("Login success \n"); //
			try {
				App.setRoot("HomePage"); /* change to cataloooooooooooggggggg */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgstring.equalsIgnoreCase("#LoginWarning"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
		}
		else if(msgstring.equalsIgnoreCase("#BlockedAccount"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
			try {
				App.setRoot("HomePage");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
