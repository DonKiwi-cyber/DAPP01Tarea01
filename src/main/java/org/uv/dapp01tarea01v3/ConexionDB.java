/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01tarea01v3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ian
 */
public class ConexionDB {
    private static ConexionDB cx = null;
    private Connection con = null;
    
    public static ConexionDB getInstance(){
        if (cx == null){
            cx = new ConexionDB();
        }
        return cx; //Se puede pero no se debe
    }
    
    private ConexionDB(){
        try {
            String url = "jdbc:postgresql://localhost:5432/dapptarea01";
            String pwd = "ian211002";
            String usr = "testuser";
            con = DriverManager.getConnection(url, usr, pwd);
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, "Se conectó");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
        
    public boolean execute(String sql){
        Statement st= null;
        try {
            st=con.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null, ex);
            return false;
        }
        finally{
            if(st!=null){
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null, ex);
                }
            }
        }
    }
    
    public ResultSet select(String sql){
        Statement st = null;
        ResultSet reg = null;
        try {
            st= con.createStatement();
            reg = st.executeQuery(sql);
            return reg;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null, ex);
            return null;
        } finally{
            if(st != null){
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null, ex);
                }
            }
        }
    }
    
    public boolean execute(TransactionDB tra){
        return tra.execute(con);
    }
}
