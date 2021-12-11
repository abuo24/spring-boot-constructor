package uz.alif.server.loader;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.alif.server.entity.Admin;
import uz.alif.server.entity.Role;
import uz.alif.server.repository.AdminRepository;
import uz.alif.server.repository.RoleRepository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

//    public DataLoader(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

    @Override
    public void run(String... args) throws Exception {

        try {
            if (init.equalsIgnoreCase("create")) {
                Role roleUser = new Role();
                roleUser.setName("ROLE_USER");
                Role roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");
                List<Role> roleList = new ArrayList<>(Arrays.asList(roleUser, roleAdmin));

                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("root"));
                admin.setFullname("admin");
                admin.setSocial("t.me/test");
                admin.setRoles(roleRepository.saveAll(roleList));
                adminRepository.save(admin);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }
    }
}
