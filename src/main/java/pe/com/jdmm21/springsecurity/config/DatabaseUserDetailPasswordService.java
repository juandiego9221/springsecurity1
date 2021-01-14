package pe.com.jdmm21.springsecurity.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;
import pe.com.jdmm21.springsecurity.model.UserCredentials;
import pe.com.jdmm21.springsecurity.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class DatabaseUserDetailPasswordService implements UserDetailsPasswordService {

    private final UserRepository userRepository;
    public final UserDetailsMapper userDetailsMapper;

    public DatabaseUserDetailPasswordService(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        UserCredentials userCredentials = userRepository.findByUsername(user.getUsername());
        userCredentials.setPassword(newPassword);
        return userDetailsMapper.toUserDetails(userCredentials);
    }
}
