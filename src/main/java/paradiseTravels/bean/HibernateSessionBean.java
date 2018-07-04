package paradiseTravels.bean;


import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class HibernateSessionBean {


    private SessionFactory sessionFactory;
    private Session session;
    public HibernateSessionBean() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public Session getSession() {
        return session;
    }
}