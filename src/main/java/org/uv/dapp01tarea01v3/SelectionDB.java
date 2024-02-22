/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01tarea01v3;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author ian
 */
public abstract class SelectionDB<T> {
    
    protected T t;
    
    public SelectionDB(T t){
        this.t = t;
    }
    
    public abstract List<T> select(Connection con);
}
