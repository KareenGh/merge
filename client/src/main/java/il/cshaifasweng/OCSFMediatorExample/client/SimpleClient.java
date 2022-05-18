package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Confirmation;
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
			System.out.print("ana hooooooooooooon");
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}
		else if(msgstring.equalsIgnoreCase("#SignUpWarning"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
		}
		else if(msgstring.equalsIgnoreCase("#SignUpSuccess"))
		{
			EventBus.getDefault().post(new ConfirmationEvent((Confirmation) ((Message) msg).getObject()));
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
