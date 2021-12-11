package uz.alif.server.controller;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.alif.server.entity.User;
import uz.alif.server.model.Result;
import uz.alif.server.payload.LoginPayload;
import uz.alif.server.payload.UserPayload;
import uz.alif.server.repository.UserRepository;
import uz.alif.server.security.JwtTokenProvider;
import uz.alif.server.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginPayload payload) {
        User user = userRepository.findByUsername(payload.getUsername()).orElseThrow(() -> new RuntimeException("user not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword()));
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        if (token == null) {
            return new ResponseEntity(new Result(false, "something went wrong"), HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> map = new HashMap();
        map.put("token", token);
        map.put("username", user.getUsername());
        map.put("success", true);
        return ResponseEntity.ok(map);
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserPayload userPayload) {
        return ResponseEntity.ok(userService.save(userPayload));
    }

}
