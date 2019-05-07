package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
 
    private static final SessionFactory sessionFactory;
 
    static {
    	/*to connect to database after git clone to the following:
    	  got to -> src/main/resources 
    	  create ->  database.properties file sibling to hibernate.cfg.xml file
    	  -> have the following properties
    	    hibernate.connection.url = jdbc:oracle:thin:@YOUR_DB_ENDPOINT:1521:ORCL
			hibernate.connection.username = YOUR_DB_USERNAME
			hibernate.connection.password = YOUR_DB_PASSWORD
    	 */
    	Properties dbProperties = getProperties();
        try {
        	Configuration configObj = new Configuration().configure("hibernate.cfg.xml");
        	configObj.setProperty("hibernate.connection.url", dbProperties.getProperty("hibernate.connection.url"));
        	configObj.setProperty("hibernate.connection.username", dbProperties.getProperty("hibernate.connection.username"));
        	configObj.setProperty("hibernate.connection.password", dbProperties.getProperty("hibernate.connection.password"));
            ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
            sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
            
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
	private static Properties getProperties() {
			
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/database.properties")) {
        // load a properties file
        prop.load(input);
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
		return prop;
	}

}
