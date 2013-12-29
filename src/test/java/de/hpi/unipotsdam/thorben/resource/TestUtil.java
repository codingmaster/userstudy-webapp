package de.hpi.unipotsdam.thorben.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.transaction.internal.jdbc.JdbcTransactionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class TestUtil {

  private static SessionFactory sessionFactory;
  private static ServiceRegistry serviceRegistry;

  private static String HIBERNATE_CFG_LOCATION = "hibernate/hibernate.cfg.xml";
  private static String DB_PROPERTIES_LOCATION = "database/database.properties";
  
  private static String DB_URL_PARAM_NAME = "jdbc.url";
  private static String DB_DRIVER_PARAM_NAME = "jdbc.driverClassName";
  private static String DB_USER_PARAM_NAME = "jdbc.username";
  private static String DB_PASSWORD_NAME = "jdbc.password";

  static {
    try {
      Properties databaseProperties = new Properties();
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      InputStream is = classLoader.getResourceAsStream(DB_PROPERTIES_LOCATION);
      databaseProperties.load(is);
      is.close();

      Configuration configuration = new Configuration()
          .configure(HIBERNATE_CFG_LOCATION);
      configuration.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
      configuration.setProperty(AvailableSettings.TRANSACTION_STRATEGY, JdbcTransactionFactory.class.getName());
      configuration.getProperties().remove(AvailableSettings.DEFAULT_SCHEMA);
      configuration.setProperty(Environment.URL, databaseProperties.getProperty(DB_URL_PARAM_NAME));
      configuration.setProperty(Environment.DRIVER, databaseProperties.getProperty(DB_DRIVER_PARAM_NAME));
      configuration.setProperty(Environment.USER, databaseProperties.getProperty(DB_USER_PARAM_NAME));
      configuration.setProperty(Environment.PASS, databaseProperties.getProperty(DB_PASSWORD_NAME));

      serviceRegistry = new ServiceRegistryBuilder().applySettings(
          configuration.getProperties()).buildServiceRegistry();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (HibernateException he) {
      System.err.println("Error creating Session: " + he);
      throw new ExceptionInInitializerError(he);
    } catch (IOException e) {
      System.err.println("Error creating Session: " + e);
      throw new ExceptionInInitializerError(e);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
