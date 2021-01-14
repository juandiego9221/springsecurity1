package pe.com.jdmm21.springsecurity.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pe.com.jdmm21.springsecurity.model.UserCredentials;

@Component
public class UserDetailsMapper {
    UserDetails toUserDetails(UserCredentials userCredentials) {
        return User.withUsername(userCredentials.getUsername()).password(userCredentials.getPassword())
                .roles("USER").build();
    }
}
