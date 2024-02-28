/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.dapp01tarea01v4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ian
 */
public class DAPP01Tarea01V4 {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        
        PojoEmpleado emp = new PojoEmpleado();
        emp.setNombre("Ian");
        emp.setDireccion("caoba 4");
        emp.setTelefono("272135");
        
        Session session = sf.getCurrentSession();
        Transaction tra = session.beginTransaction();
        
        session.save(emp);
        tra.commit();
        
    }
}
