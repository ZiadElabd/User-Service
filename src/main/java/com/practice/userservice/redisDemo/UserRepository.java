package com.practice.userservice.redisDemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<RedisUser, UUID> {
    RedisUser findByEmailAndPassword(String email, String password);
}
