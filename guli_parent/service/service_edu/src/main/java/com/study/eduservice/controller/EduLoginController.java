package com.study.eduservice.controller;

import com.study.commonutils.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin    //解决跨域
public class EduLoginController {
    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.success().data("token","admin");
    }

    @GetMapping("/info")
    public ResponseEntity info(){
        List<String> roles=new ArrayList<>();
        roles.add("admin");
        return ResponseEntity.success().data("roles",roles).data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif").data("introduction","I am a super administrator");
    }
}
