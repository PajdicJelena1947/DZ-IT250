/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.it250.dz.dao;

import com.mycompany.it250.dz.entities.AbstractEntity;
import java.util.List;

/**
 *
 * @author pc
 */
public interface GenericDao <T extends AbstractEntity>  {
    public abstract T merge(T obj);

    public abstract T delete(Integer idOfObj, Class klasa);

    public abstract List<T> loadAllActive(Class klasa);

    public abstract T getElementById(Integer id, Class klasa);
}
