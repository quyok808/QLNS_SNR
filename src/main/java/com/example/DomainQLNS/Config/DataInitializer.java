package com.example.DomainQLNS.Config;

import com.example.DomainQLNS.Models.User;
import com.example.DomainQLNS.Repository.RoleRepository;
import com.example.DomainQLNS.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.DomainQLNS.Models.Role;

import java.util.HashSet;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(1L,"ADMIN","Admin",null));
            roleRepository.save(new Role(2L,"HR","Human Resources",null));
            roleRepository.save(new Role(3L,"USER","User",null));
        }
        Role adminRole = roleRepository.findRoleById(1L);
        if (userRepository.count() == 0){
            // Check if an admin user exists
            Optional<User> adminUserOptional = userRepository.findByUsername("admin");
            if (!adminUserOptional.isPresent()) {
                // Create admin user
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin")); // Encode the password
                adminUser.setEmail("admin@gmail.com"); // Set a default email
                adminUser.setPhone("0999999999");
                adminUser.getRoles().add(adminRole);
                userRepository.save(adminUser);
            }
        }
    }
}
