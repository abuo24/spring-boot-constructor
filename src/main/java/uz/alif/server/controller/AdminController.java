package uz.alif.server.controller;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.server.model.Result;
import uz.alif.server.payload.UserPayload;
import uz.alif.server.repository.UserRepository;
import uz.alif.server.service.UserService;

import java.util.UUID;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600, origins = "*")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody UserPayload userPayload) {
        return userService.editById(id,userPayload);
    }

}
