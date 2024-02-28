/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.uv.dapp01tarea01v4;

import java.util.List;

/**
 *
 * @author ian
 */
public interface IDAOGeneral<T, ID> {
    
    public boolean save(T t);
    public boolean update(T t, ID id);
    public boolean delete(ID id);
    public T findByID(ID id);
    public List<T> findAll();
}
