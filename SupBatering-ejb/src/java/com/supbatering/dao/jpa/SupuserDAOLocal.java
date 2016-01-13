/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.dao.jpa;

import com.supbatering.entity.Supobject;
import com.supbatering.entity.Supuser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author oumartraore
 */
@Local
public interface SupuserDAOLocal {

    void Create(Supuser supuser);

    void Edit(Supuser supuser, Supuser supuserNew);

    Supuser FindOne(String username, String password);

    List<Supuser> FindAll();

}
