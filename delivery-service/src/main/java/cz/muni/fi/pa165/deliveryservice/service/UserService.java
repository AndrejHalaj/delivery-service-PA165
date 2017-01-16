/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;


import cz.muni.fi.pa165.deliveryservice.entity.Useraccount;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristian Mateka
 */
@Service
public interface UserService {
    void register(Useraccount user, String password);
    
    Useraccount getUserById(Long id);
    
    Useraccount getUserByEmail(String email);
    
    boolean authenticate(Useraccount user, String password);
}
