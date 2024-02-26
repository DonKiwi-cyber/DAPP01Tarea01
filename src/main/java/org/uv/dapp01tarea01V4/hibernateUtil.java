/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01tarea01V4;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ian
 */
public class hibernateUtil {
    private static final SessionFactory sf;
    
    static{
        try{
            sf = new Configuration().configure("hibernate.cfg.xml.xsd").buildSessionFactory();
        } catch(Throwable ex){
            System.err.println("Initial sessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
