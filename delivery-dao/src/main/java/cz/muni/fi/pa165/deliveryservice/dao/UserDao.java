/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.model.User;

/**
 *
 * @author Kristian Mateka
 */
public interface UserDao {
  
    /**
     * creates given user
     *
     * @param user
     */
    void create(User user);

    /**
     *
     * @param id id of user to find
     * @return user by id returns user by given id if exists exception
     * is thrown otherwise
     */
    User findById(Long id);
}
