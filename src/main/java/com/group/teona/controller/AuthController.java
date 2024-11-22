package com.group.teona.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {


    @GetMapping("test")
    public ResponseEntity tested (){
        return ResponseEntity.ok("it a test");
    }


}

