package com.depo.userservice.runner;

import com.depo.userservice.entity.User;
import com.depo.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final UserService userService;

    public DatabaseInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        //USERS.forEach(userService::save);
    }


    public static final List<User> USERS = Arrays.asList(
            new User( "user1", "John", "Doe", "password1", "user1@example.com", "12345678908"),
            new User( "admin", "John", "Doe", "admin", "admin@example.com", "1234567890"),
            new User( "user", "John", "Doe", "user", "user@example.com", "12345678905"),
            new User( "user2", "Jane", "Smith", "password2", "user2@example.com", "9876543210"),
            new User("user3", "Alice", "Johnson", "password3", "user3@example.com", "5555555555"),
            new User( "user4", "Bob", "Brown", "password4", "user4@example.com", "6666666666"),
            new User("user5", "Eva", "Williams", "password5", "user5@example.com", "7777777777"),
            new User("user6", "Michael", "Wilson", "password6", "user6@example.com", "8888888888"),
            new User( "user7", "Olivia", "Taylor", "password7", "user7@example.com", "9999999999"),
            new User( "user8", "David", "Brown", "password8", "user8@example.com", "1111111111"),
            new User( "user9", "Sophia", "Jones", "password9", "user9@example.com", "2222222222"),
            new User( "user10", "Liam", "Davis", "password10", "user10@example.com", "3333333333")
    );


}
