package waa.labs.waalabs.dataseeder;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.repo.RoleRepo;
import waa.labs.waalabs.domain.Role;
import waa.labs.waalabs.repo.UserRepo;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationRunner {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Ensure roles exist
        Role adminRole = roleRepo.findRoleByName("ADMIN");
        if (adminRole == null) {
            adminRole = Role.builder().role("ADMIN").build();
            roleRepo.save(adminRole);
        }

        Role userRole = roleRepo.findRoleByName("USER");
        if (userRole == null) {
            userRole = Role.builder().role("USER").build();
            roleRepo.save(userRole);
        }

        // Ensure an admin user exists
        if (userRepo.findUserByEmail("admin@example.com") == null) {
            User adminUser = User.builder()
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .firstname("Super")
                    .lastname("Admin")
                    .roles(List.of(adminRole))
                    .build();
            userRepo.save(adminUser);
            System.out.println("Admin user created: admin@example.com / admin123");
        } else {
            System.out.println("Admin user already exists");
        }
    }
}
