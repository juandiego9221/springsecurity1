package pe.com.jdmm21.springsecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.com.jdmm21.springsecurity.dto.UserCredentialsDto;
import pe.com.jdmm21.springsecurity.model.UserCredentials;
import pe.com.jdmm21.springsecurity.repository.UserRepository;

@RestController
public class Registration {
    private static Logger logger = LoggerFactory.getLogger(Registration.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Registration(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody UserCredentialsDto userCredentialsDto) {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEnabled(true);
        userCredentials.setUsername(userCredentialsDto.getUsername());
        String encode = passwordEncoder.encode(userCredentialsDto.getPassword());
        logger.info("password encode: "+encode);
        userCredentials.setPassword(encode);
        userRepository.save(userCredentials);
    }

}
