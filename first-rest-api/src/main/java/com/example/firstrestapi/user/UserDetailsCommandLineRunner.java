package com.example.firstrestapi.user;

import org.h2.engine.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
    public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
        this.repository = repository;
    }

    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserDetailsRepository repository;


    @Override
    public void run(String... args) throws Exception {
        repository.save(new UserDetails("Ranga", "Admin"));
        repository.save(new UserDetails("Rava", "Admin"));
        repository.save(new UserDetails("John", "User"));

        System.out.println("--------------------Usuarios por role-----------------");
        List<UserDetails> users = repository.findAll();
        List<UserDetails> usersByRole = repository.findByRole("Admin");
        usersByRole.forEach(userDetailsbyRole -> logger.info(userDetailsbyRole.toString()));

        System.out.println("--------------------Todos los usuarios-----------------");
        users.forEach(userDetails -> logger.info(users.toString()));
        logger.info(Arrays.toString(args));
    }
}
