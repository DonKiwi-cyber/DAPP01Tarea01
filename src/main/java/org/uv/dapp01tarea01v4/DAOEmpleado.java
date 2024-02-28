/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01tarea01v4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ian
 */
public class DAOEmpleado implements IDAOGeneral<PojoEmpleado, Integer>{
    
    SessionFactory sf = null;
    Transaction tra = null;
    Session session = null;

    @Override
    public boolean save(PojoEmpleado t) {
        boolean success = false;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            session.save(t);
            tra.commit();
            session.close();
            success = true;
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
            }
        } 
        return success;
    }

    @Override
    public boolean update(PojoEmpleado t, Integer id) {
        boolean success = false;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            PojoEmpleado emp = session.get(PojoEmpleado.class, id);
            if (emp != null){
                session.update(t);
                tra.commit();
                success = true;
            }
            session.close();
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
            }
        } 
        return success;
    }

    @Override
    public boolean delete(Integer id) {
        boolean success = false;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            PojoEmpleado emp = session.get(PojoEmpleado.class, id);
            if (emp != null){
                session.delete(id);
                tra.commit();
                success = true;
            }
            session.close();
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
            }
        } 
        return success;
    }

    @Override
    public PojoEmpleado findByID(Integer id) {
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            PojoEmpleado emp = session.get(PojoEmpleado.class, id);
            session.close();
            return emp;
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
            }
            return null;
        } 
    }

    @Override
    public List<PojoEmpleado> findAll() {
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            List<PojoEmpleado> emp = session.createQuery("From empleadotemporal emp order by emp.id").list();
            tra.commit();
            session.close();
            return emp;
        }
        catch(HibernateException e){
            if (tra != null) {
                tra.rollback();
            }
            return null;
        }
    }
    
}
