/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01tarea01v3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ian
 */
public class DAOEmpleado implements IDAOGeneral<Empleado, Integer>{
    
    boolean res = false;
    ResultSet rset = null;

    @Override
    public boolean save(Empleado t) {
        ConexionDB con= ConexionDB.getInstance();
        TransactionDB tra = new TransactionDB(t){
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="insert into empleadotemporal (nombre, direccion, telefono) values (?,?,?)";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setString(1, t.getNombre());
                    pstm.setString(2, t.getDireccion());
                    pstm.setString(3, t.getTelefono());
                    pstm.execute();
                    
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        return con.execute(tra);
    }

    @Override
    public boolean update(Empleado t, Integer id) {
        ConexionDB con= ConexionDB.getInstance();
        TransactionDB tra = new TransactionDB(t){
            @Override
            public boolean execute(Connection con) {
                try{
                    String sql = "UPDATE empleadotemporal SET nombre = ?, direccion = ?, telefono = ?"
                        +" WHERE empleados.clave = ?";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setString(1, t.getNombre());
                    pstm.setString(2, t.getDireccion());
                    pstm.setString(3, t.getTelefono());
                    pstm.setInt(4, id);
                    pstm.execute();
                    return true;
                } catch (SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex.toString());
                    return false;
                }
            }
        };
        return con.execute(tra);
    }

    @Override
    public boolean delete(Integer id) {
        ConexionDB con= ConexionDB.getInstance();
        TransactionDB tra = new TransactionDB(id){
            @Override
            public boolean execute(Connection con) {
                try{
                    String sql = "DELETE FROM empleadotemporal WHERE empleados.clave = ?";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setInt(1,id);
                    pstm.execute();
                    return true;
                } catch (SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex.toString());
                    return false;
                }
            }
        };
        return con.execute(tra);
    }

    @Override
    public Empleado findByID(Integer id) {
        ConexionDB con = ConexionDB.getInstance();
        SelectionDB sel = new SelectionDB(){
            @Override
            public List find(Connection con) {
                try {
                    String sql = "select * from empleadotemporal";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    rset = pstm.executeQuery();
                    List list = new ArrayList<Empleado>();
                    while(rset.next()){
                        Empleado emp = new Empleado();
                        emp.setId(rset.getInt(1));
                        emp.setNombre(rset.getString(2));
                        emp.setDireccion(rset.getString(3));
                        emp.setTelefono(rset.getString(4));
                        list.add(emp);
                    }
                    return list;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }
        };
        List list = con.select(sel);
        Empleado emp = (Empleado)list.get(id);
        return emp;
    }

    @Override
    public List<Empleado> findAll() {
        ConexionDB con = ConexionDB.getInstance();
        SelectionDB sel = new SelectionDB(){
            @Override
            public List find(Connection con) {
                try{
                List<Empleado> lstEmpleado = new ArrayList<>();
                String sql = "Select * from empleadotemporal";
                Statement st = con.createStatement();
                ResultSet reg = st.executeQuery(sql);
                
                while(reg.next()){
                    Empleado emp = new Empleado();
                    emp.setId(reg.getInt(1));
                    emp.setNombre(reg.getString(2));
                    emp.setDireccion(reg.getString(3));
                    emp.setTelefono(reg.getString(4));
                    lstEmpleado.add(emp);
                }
                return lstEmpleado;
                } catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex.toString());
                    return null;
                }
            }  
        };
        return con.select(sel);
    }
    
}
