package pe.com.jdmm21.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DatabaseUserDetailsService databaseUserDetailsService;
    private final DatabaseUserDetailPasswordService databaseUserDetailPasswordService;

    public SecurityConfig(DatabaseUserDetailsService databaseUserDetailsService, DatabaseUserDetailPasswordService databaseUserDetailPasswordService) {
        this.databaseUserDetailsService = databaseUserDetailsService;
        this.databaseUserDetailPasswordService = databaseUserDetailPasswordService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.databaseUserDetailsService);
        daoAuthenticationProvider.setUserDetailsPasswordService(this.databaseUserDetailPasswordService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
