package com.practice.userservice.redisDemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisUserService redisUserService;

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        redisUserService.addUser(user);
    }

    @GetMapping("/redisLogin")
    public RedisUser redisLogin(@RequestBody User user){
        return redisUserService.getUser(user);
    }
}
