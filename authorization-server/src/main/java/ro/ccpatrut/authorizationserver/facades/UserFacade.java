package ro.ccpatrut.authorizationserver.facades;


import ro.ccpatrut.authorizationserver.dto.UserDTO;

import java.util.List;

public interface UserFacade {
    void save(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO getByEmail(String email);


    void delete(UserDTO userDTO);
}
