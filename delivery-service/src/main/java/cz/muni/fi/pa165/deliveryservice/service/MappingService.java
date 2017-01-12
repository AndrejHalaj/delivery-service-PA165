/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Kristian Mateka
 */
@Service
public interface MappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    
    public Mapper getMapper();
}
