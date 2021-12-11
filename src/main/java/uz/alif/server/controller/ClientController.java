package uz.alif.server.controller;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.server.model.Result;
import uz.alif.server.payload.UserPayload;
import uz.alif.server.service.UserService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {

   private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserPayload userPayload){
        return userService.save(userPayload)? ResponseEntity.ok(new Result(true, "created")):new ResponseEntity(new Result(false, "not created"), HttpStatus.BAD_REQUEST);
    }


}
