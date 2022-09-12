package com.practice.userservice.redisDemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtility {

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    Gson gson = new GsonBuilder().create();

    public void setUser(final String key, User user){
        redisTemplate.opsForValue().set(key, gson.toJson(user));

        redisTemplate.expire(key, 10, TimeUnit.HOURS);

    }

    public User getUser(final String key) {
        redisTemplate.opsForValue().set(key, gson.toJson(new User("ziad", "1234")));
        String s = redisTemplate.opsForValue().get(key);
        return gson.fromJson(s, User.class);
    }


    public void deleteUser(final String key){
        redisTemplate.delete(key);
    }

}
