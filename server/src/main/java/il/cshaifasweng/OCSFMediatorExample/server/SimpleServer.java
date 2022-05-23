package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

//import javax.imageio.spi.ServiceRegistry;
//import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class SimpleServer extends AbstractServer {
    private static Session session;
    private static SessionFactory sessionFactory = getSessionFactory();

    public SimpleServer(int port) {
        super(port);
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Registration.class);
//		configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass((Message.class));

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void initializeData() {
        Registration client1 = new Registration("Kareen", "Ghattas", "123456789", "kareen@gmail.com", "0505123456", "KareenGh", "123456789", "client", "2233445566", "1/1/2023", "Store Account");
        Registration client2 = new Registration("Natalie", "Nakkara", "234789456", "Natalie@gmail.com", "0524789000", "NatalieNK", "22nN90999", "client", "1234561299", "5/8/2024", "Chain Account");
        Registration CEO = new Registration("Rashil", "Mbariky", "4443336661", "", "", "Rashi", "anabajesh3ljam3a", "CEO", "", "", "");
        Registration NetworkMarketingWorker = new Registration("Eissa", "Wahesh", "", "", "", "Eissa", "11111", "NetworkMarketingWorker", "", "", "");
        Registration SystemManger = new Registration("Elias", "Dow", "", "", "", "TheKing", "lampon", "SystemManger", "", "", "");
        Registration BranchManger = new Registration("Saher", "Daoud", "", "", "", "Saher", "123456", "BranchManger", "", "", "");

        session.save(client1);
        session.save(client2);
        session.save(CEO);
        session.save(NetworkMarketingWorker);
        session.save(SystemManger);
        session.save(BranchManger);
        session.flush();
    }

    public static <T> List<T> getAll(Class<T> object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        String msgString = msg.toString();
        if (msgString.startsWith("#warning")) {
            Warning warning = new Warning("Warning from server!");
            try {
                client.sendToClient(warning);
                System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#SignUpRequest")) {
//			Registration newSignUp = new Registration();
            Message msg1 = ((Message) msg);
            Registration newSignUp = (Registration) msg1.getObject();
            String ID1 = newSignUp.getClient_ID(); //(String) msg1.getObject();
            session = sessionFactory.openSession();
            try {
                List<Registration> clients = getAll(Registration.class);
                for (Registration registration : clients) {
                    if (registration.getClient_ID().equalsIgnoreCase(ID1)) {
//                        System.out.print("foundddddddddddd\n");
//							registration.setRegistered(true);
                        Warning new_warning = new Warning("Dear Client,you are already Signed up.\n Please go to Login.");
                        client.sendToClient(new Message("#SignUpWarning", new_warning));
                        return;
                    } else {
                        session.beginTransaction();
                        session.save(newSignUp);
                        session.flush();
                        session.getTransaction().commit();
                        Confirmation confirmation = new Confirmation("Dear " + newSignUp.getUserName() + " welcome to Lilach. \n You have been signed up successfully");
                        client.sendToClient(new Message("#SignUpSuccess", confirmation));
                        return;
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#LoginRequest")) {
            Message msg1 = ((Message) msg);
            User newLogin = (User) msg1.getObject();
            session = sessionFactory.openSession();
            String UserName = newLogin.getUserName();
            String Password = newLogin.getPassword();
            try{
                List<Registration> clients = getAll(Registration.class);
                for (Registration registration : clients){
                    if(registration.getUserName().equalsIgnoreCase(UserName)){
                        if(registration.getPassword().equalsIgnoreCase(Password)){
                            if(registration.getStatus().equalsIgnoreCase("blocked client")){
                                Warning new_warning = new Warning("You're account have been blocked. Please contact customer service");
                                client.sendToClient(new Message("#BlockedAccount", new_warning));
                                return;
                            }
                            else{
                                client.sendToClient(new Message("#LogInSucess", registration));
                                return;
                            }
                        }
                        else{
                            Warning new_warning = new Warning("You have entered invalid input. Please try again ");
                            client.sendToClient(new Message("#LoginWarning", new_warning));
                            return;
                        }
                    }
                    else{
                        Warning new_warning = new Warning("You have entered invalid input. Please try again ");
                        client.sendToClient(new Message("#LoginWarning", new_warning));
                        return;
                    }

                }

            }catch (Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
//		else if(msgString.startsWith("#CheckID")) {
////			Registration found = new Registration();
//////			Boolean IdFound = found.getRegistered();
////			System.out.print("ana hooon");
//			Message msg1 = ((Message) msg);
//			String ID1 = (String) msg1.getObject();
////			System.out.print(ID1 + "\n");
//			session = sessionFactory.openSession();
//			try {
//				List<Registration> clients = getAll(Registration.class);
//				for(Registration registration : clients){
//					if(registration.getClient_ID().equalsIgnoreCase(ID1)){
//						System.out.print("foundddddddddddd\n");
//						Warning new_warning = new Warning("Dear Worker,\nyou are already logged in");
//						client.sendToClient(new Message("#LoginWarning", new_warning));
////						found.setRegistered(true);
//						return;
//					}
////					else
////					{
////						found.setRegistered(false);
////					}
//				}
//
//			} catch (Exception e){
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
    }

    public void connectData() {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            initializeData();
            session.close();
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
                session.getSessionFactory().close();
            }
        }
    }
}
