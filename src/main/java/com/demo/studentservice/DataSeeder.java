package com.demo.studentservice;

import com.demo.studentservice.entity.AppUser;
import com.demo.studentservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@org.springframework.context.annotation.Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seed(UserRepository repo, BCryptPasswordEncoder encoder) {
        return args -> {
            if (!repo.findByUsername("admin").isPresent()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setEnabled(true);

                Set<String> roles = new HashSet<String>();
                roles.add("ROLE_ADMIN");
                roles.add("ROLE_USER");
                admin.setRoles(roles);

                repo.save(admin);
            }

            if (!repo.findByUsername("user").isPresent()) {
                AppUser user = new AppUser();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setEnabled(true);

                Set<String> roles = new HashSet<String>();
                roles.add("ROLE_USER");
                user.setRoles(roles);

                repo.save(user);
            }
        };
    }
}