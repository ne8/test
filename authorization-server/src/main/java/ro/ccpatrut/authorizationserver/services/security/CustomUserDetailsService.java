package ro.ccpatrut.authorizationserver.services.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ccpatrut.authorizationserver.entities.UserEntity;
import ro.ccpatrut.authorizationserver.entities.UserRoleEntity;
import ro.ccpatrut.authorizationserver.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private static final String STATE_ACTIVE = "Active";
    private static final String ROLE_PREFIX_REQUIRED_BY_SPRING_SECURITY_CONTEXT = "ROLE_";

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String nickname) throws UsernameNotFoundException {
        log.debug("trying to fetch user with nickname:" + nickname);
        final UserEntity userEntity = this.userService.findByUsername(nickname);
        if (userEntity == null) {
            log.error("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        final User springSecurityUser = new User(userEntity.getUsername(), userEntity.getPassword(),
                userEntity.getState().equals(STATE_ACTIVE), userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(), userEntity.isAccountNonLocked(), CustomUserDetailsService.getGrantedAuthorities(userEntity));

        return springSecurityUser;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(final UserEntity userEntity) {
        final List<GrantedAuthority> authorities = new ArrayList<>();

        for (final UserRoleEntity userRoleType : userEntity.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX_REQUIRED_BY_SPRING_SECURITY_CONTEXT + userRoleType.getType()));
        }
        return authorities;
    }
}
