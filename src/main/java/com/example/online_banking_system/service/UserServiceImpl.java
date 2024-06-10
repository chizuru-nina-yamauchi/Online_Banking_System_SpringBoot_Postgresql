package com.example.online_banking_system.service;

import com.example.online_banking_system.model.Account;
import com.example.online_banking_system.model.AppUser;
import com.example.online_banking_system.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 *  UserServiceImpl class implements the UserDetailsService interface.
 *  It provides methods to save a user, find a user by username, and check the password.
 *  It also provides a method to load a user by username.
 */


@Service // Indicates that this class is a Spring service component
public class UserServiceImpl implements UserDetailsService{

    @Autowired // Automatically injects the AppUserRepository dependency
    private AppUserRepository userRepository;

    @Autowired // Automatically injects the BCryptPasswordEncoder dependency
    private BCryptPasswordEncoder bCryptPasswordEncoder;


     // Save a user with the given username and encoded password
    public void saveUser(String username, String password) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password)); // Encode the password before saving
        userRepository.save(user); // Save the user to the database
    }



    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkPassword (String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword); // Check if the raw password matches the encoded password

    }

    // Load a user by username. This method is required by the UserDetailsService interface.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User // Create a new UserDetails object
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    // Extra service to save user with accounts
    public void saveUserWithAccounts(String userName, String password, String accountType, List<Account> accounts) {
        if (userName == null || accounts == null) {
            throw new IllegalArgumentException("Username and accounts list cannot be null");
        }

        // Create a new user with the given username and encoded(encrypted) password
        AppUser user = new AppUser(userName, bCryptPasswordEncoder.encode(password));

        // Set the user for each account in the list
        for (Account account : accounts) {
            account.setUser(user);
        }

        // Set the list of accounts for the user
        user.setAccounts(accounts);

        // Save the user to the database
        userRepository.save(user);
    }



}
