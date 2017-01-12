/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.model.User;

/**
 *
 * @author Kristian Mateka
 */
public interface UserService {
    void register(User user, String password);
    
    User getUserById(Long id);
    
    boolean authenticate(User user, String password);
}
