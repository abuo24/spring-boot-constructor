package uz.alif.server.controller;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.server.entity.User;
import uz.alif.server.repository.UserRepository;
import uz.alif.server.security.SecurityUtils;
import uz.alif.server.security.SecurityUtils.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(maxAge = 3600L)
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity getMe(){
        User user = userRepository.findByUsername(SecurityUtils.getCurrentUsername().orElseThrow(()->new RuntimeException("username not founsd"))).orElseThrow(()->new RuntimeException("user not found"));
        return ResponseEntity.ok(user);
    }
    @GetMapping("/")
    public ResponseEntity getAll(@RequestParam int page,@RequestParam int size){
        return ResponseEntity.ok(userRepository.findAll(PageRequest.of(page,size)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
