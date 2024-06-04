package com.example.online_banking_system.config;


import com.example.online_banking_system.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
@EnableWebSecurity // Enables Spring Security's web security support and provides the Spring MVC integration.
public class SecurityConfig {

    @Bean // This annotation is used at the method level. The @Bean annotation works with @Configuration to create Spring beans.
    public UserDetailsService userDetailsService(UserServiceImpl userService) {

        return userService;

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // SecurityFilterChain is a type of WebSecurityConfigurerAdapter that allows to customize the security filter chain.
        http
            .authorizeRequests(authorizeRequests -> // Allows restricting access based upon the HttpServletRequest using RequestMatcher implementations.
                    authorizeRequests
                    .requestMatchers("signup", "/login").permitAll() // Allow access to the signup and login pages without authentication
                    .anyRequest().authenticated() // All other requests require authentication
            )
            .formLogin(formLogin ->
                    formLogin
                    .loginPage("/login") // Specify the login page
                    .defaultSuccessUrl("/", true) // Redirect to the home page after successful login
                    .permitAll() // Allow everyone to access the login page
            )
            .logout(logout ->
                logout
                    .logoutUrl("/logout") // Specify the logout URL
                    .logoutSuccessUrl("/login?logout") // Redirect to the login page after successful logout
                    .permitAll() // Allow everyone to access the logout URL
            );
        return http.build(); // Build the security filter chain
    }

    // Configure the global authentication manager
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); // Set up authentication manager with user details service and password encoder

    }



}
