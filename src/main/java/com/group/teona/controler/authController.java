package com.group.teona.controler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class authController {


    @GetMapping("test")
    public ResponseEntity tested (){
        return ResponseEntity.ok("it a test");
    }


}

