/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ead_assignment;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Nutt
 */
public class HibernateUtil {
     private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure()
                    .buildSessionFactory();
        }catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed."+ ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionfactory(){
        return sessionFactory;
    }
    
    public static Session openSession(){
        return sessionFactory.openSession();
    }
    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    public static void close(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }
}
