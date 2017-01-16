package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.user.UserDTO;
import cz.muni.fi.pa165.deliveryservice.facade.UserFacade;
import cz.muni.fi.pa165.deliveryservice.entity.Useraccount;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.UserService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kristian Mateka
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private MappingService mapper;

    @Inject
    private UserService service;

    @Override
    public UserDTO findById(Long id) {
        Useraccount user = service.getUserById(id);
        return (user == null) ? null : mapper.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        Useraccount user = service.getUserByEmail(email);
        return mapper.mapTo(user, UserDTO.class);
    }

    @Override
    public boolean authenticate(UserDTO userDto) {
        Useraccount c = service.getUserByEmail(userDto.getEmailAddress());
        return service.authenticate(c, userDto.getPassword());
    }
}
