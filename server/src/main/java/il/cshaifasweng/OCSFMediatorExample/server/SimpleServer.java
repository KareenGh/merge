package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

//import javax.imageio.spi.ServiceRegistry;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
//import il.cshaifasweng.OCSFMediatorExample.entities.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SimpleServer extends AbstractServer {
	private static Session session;
	private static SessionFactory sessionFactory = getSessionFactory();

	public SimpleServer(int port) {
		super(port);
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
		}
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

	private static void initializeData(){
		Registration client1 = new Registration("Kareen", "Ghattas" , "123456789" , "kareen@gmail.com" , "0505123456" , "KareenGh" , "123456789" , "client" , "2233445566" , "1/1/2023" , "Store Account");
		session.save(client1);

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
	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}
}
