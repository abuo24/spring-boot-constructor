package uz.alif.server.service;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.alif.server.entity.User;
import uz.alif.server.exceptions.BadRequestException;
import uz.alif.server.exceptions.ResourceNotFoundException;
import uz.alif.server.payload.UserPayload;
import uz.alif.server.repository.RoleRepository;
import uz.alif.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean save(UserPayload userPayload) {
        User user = new User();
        user.setUsername(userPayload.getUsername());
        user.setFullname(userPayload.getFullname());
        String pass = passwordEncoder.encode(userPayload.getPassword());
        user.setPassword(pass);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user)!=null;
    }
    public ResponseEntity<?> editById(UUID id, UserPayload userPayload){
        if (userRepository.existsById(id)){
            User  user= new User(userPayload.getUsername(), passwordEncoder.encode(userPayload.getPassword()), userPayload.getFullname(), new ArrayList<>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
            user.setId(id);
            System.out.println(user);
            return ResponseEntity.ok(userRepository.save(user));
        } else throw new BadRequestException("user not found id:"+id);
    }

}
