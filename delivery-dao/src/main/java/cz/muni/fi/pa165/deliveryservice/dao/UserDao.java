/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.entity.Useraccount;

/**
 *
 * @author Kristian Mateka
 */
public interface UserDao {
    
    void create(Useraccount user);

    Useraccount update(Useraccount user);

    Useraccount findById(Long id);
    
    Useraccount findByEmail(String email);
}
