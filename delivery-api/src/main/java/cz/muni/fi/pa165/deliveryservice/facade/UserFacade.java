/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.facade;

import cz.muni.fi.pa165.deliveryservice.dto.user.UserDTO;

/**
 *
 * @author Kristian Mateka
 */
public interface UserFacade {
    UserDTO findById(Long id);

    UserDTO findByEmail(String email);
    
    boolean authenticate(UserDTO userDto);
}
