package ro.ccpatrut.authorizationserver.services;


import ro.ccpatrut.authorizationserver.entities.UserEntity;

import java.util.List;

public interface UserService {
    void save(UserEntity userEntity);

    List<UserEntity> findAll();

    UserEntity getByEmail(String email);

    void delete(UserEntity userEntity);

    UserEntity findByUsername(String nickname);

}
