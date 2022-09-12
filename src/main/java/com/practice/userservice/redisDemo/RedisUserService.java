package com.practice.userservice.redisDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RedisUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RedisUtility redisUtility;

    public void addUser(User user){
        RedisUser redisUser = new RedisUser();
        redisUser.setEmail(user.getEmail());
        redisUser.setPassword(user.getPassword());

        userRepository.save(redisUser);
    }

    public RedisUser getUser(User user){
        log.info("bfgbfgggggggggg");
        User cachedUser = redisUtility.getUser(user.getEmail());

        if (cachedUser == null){
            log.info("user if not found in the cache");
            if(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()) != null){
                redisUtility.setUser(user.getEmail(), user);
                cachedUser = redisUtility.getUser(user.getEmail());
            }
        }

        RedisUser redisUser = new RedisUser();
        redisUser.setEmail(cachedUser.getEmail());
        redisUser.setPassword(cachedUser.getPassword());

        return redisUser;
    }
}
