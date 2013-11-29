package de.hpi.unipotsdam.thorben.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionHelper {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public  Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
    }
}
