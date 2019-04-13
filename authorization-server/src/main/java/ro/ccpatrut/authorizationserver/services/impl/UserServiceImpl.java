package ro.ccpatrut.authorizationserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ccpatrut.authorizationserver.entities.UserEntity;
import ro.ccpatrut.authorizationserver.entities.UserRoleEntity;
import ro.ccpatrut.authorizationserver.repository.UserRepository;
import ro.ccpatrut.authorizationserver.repository.UserRoleRepository;
import ro.ccpatrut.authorizationserver.services.UserService;
import ro.ccpatrut.authorizationserver.services.exception.AlreadyExistingUserException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(final UserEntity userEntity) {
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        final Set<UserRoleEntity> userRoleEntities = this.createAndSaveDefaultRolesForUser();
        userEntity.setUserRoles(userRoleEntities);
        if (this.userRepository.findByUsername(userEntity.getUsername()) != null || userRepository.findByEmail(userEntity.getEmail()) != null) {
            throw new AlreadyExistingUserException("User already Exists");
        }
        this.userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        final Iterable<UserEntity> userEntities = this.userRepository.findAll();
        final List<UserEntity> userEntityList = new ArrayList<>();

        for (final UserEntity userEntity : userEntities) {
            userEntityList.add(userEntity);
        }
        return userEntityList;
    }


    @Override
    public UserEntity getByEmail(final String email) {
        return this.userRepository.findByEmail(email);
    }


    @Override
    public void delete(final UserEntity userEntity) {
        this.userRepository.delete(userEntity);
    }

    @Override
    public UserEntity findByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    private Set<UserRoleEntity> createAndSaveDefaultRolesForUser() {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        final UserRoleEntity existingEntity = this.userRoleRepository.findByType(userRoleEntity.getType());
        if (existingEntity == null) {
            this.userRoleRepository.save(userRoleEntity);
        } else {
            userRoleEntity = existingEntity;
        }
        final Set<UserRoleEntity> userRoleEntitySet = new HashSet<>();
        userRoleEntitySet.add(userRoleEntity);
        return userRoleEntitySet;
    }

}
