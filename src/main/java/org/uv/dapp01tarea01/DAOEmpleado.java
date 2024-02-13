/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01tarea01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ian
 */
public class DAOEmpleado {
    
    private Connection con = null;
    private PreparedStatement st = null;
    
    public boolean guardar(PojoEmpleado emp){
        try {
            String url = "jdbc:postgresql://localhost:5432/dapptarea01";
            String pwd = "ian211002";
            String usr = "postgres";
            con = DriverManager.getConnection(url, usr, pwd);
            
            con.setAutoCommit(false);
            String sql = "insert into empleadotemporal (nombre, direccion, telefono) values "
                    + "(?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, emp.getNombre());
            st.setString(2, emp.getDireccion());
            st.setString(3, emp.getTelefono());
            st.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean eliminar(int id, String name){
        try {
            String url = "jdbc:postgresql://localhost:5432/dapptarea01";
            String pwd = "ian211002";
            String usr = "postgres";
            Connection con = DriverManager.getConnection(url, usr, pwd);
            
            con.setAutoCommit(false);
            String sql = "delete from empleadotemporal where"
                + " id = "+id+" and nombre = "+name;
            
            st = con.prepareStatement(sql);
            st.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean modificar(PojoEmpleado emp){
        try {
            String url = "jdbc:postgresql://localhost:5432/dapptarea01";
            String pwd = "ian211002";
            String usr = "postgres";
            con = DriverManager.getConnection(url, usr, pwd);
            
            con.setAutoCommit(false);
            String sql = "update empleadotemporal set "
                + "nombre = ?, direccion = ?, telefono = ? where id = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, emp.getNombre());
            st.setString(2, emp.getDireccion());
            st.setString(3, emp.getTelefono());
            st.setInt(4, emp.getId());
            st.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public PojoEmpleado buscarByID(int id){
        try {
            String url = "jdbc:postgresql://localhost:5432/dapptarea01";
            String pwd = "ian211002";
            String usr = "postgres";
            Connection con = DriverManager.getConnection(url, usr, pwd);
            
            String sql = "Select * from empleadotemporal where id = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                PojoEmpleado emp = new PojoEmpleado();
                emp.setId(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setDireccion(rs.getString(3));
                emp.setTelefono(rs.getString(4));
                return emp;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<PojoEmpleado> buscarAll(){
        try {
            String url = "jdbc:postgresql://localhost:5432/dapptarea01";
            String pwd = "ian211002";
            String usr = "postgres";
            Connection con = DriverManager.getConnection(url, usr, pwd);
            
            String sql = "Select * from empleadotemporal";
            st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            ArrayList<PojoEmpleado> list = new ArrayList<>();
            
            while(rs.next()){
                PojoEmpleado emp = new PojoEmpleado();
                emp.setId(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setDireccion(rs.getString(3));
                emp.setTelefono(rs.getString(4));
                list.add(emp);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
