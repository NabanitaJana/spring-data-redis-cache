package com.api.redis.controller;

import com.api.redis.dao.UserDao;
import com.api.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@EnableCaching
public class UserController {

    @Autowired
    private UserDao userDao;


    // create user
    @PostMapping
    public User createUser(@RequestBody User user
    ) {

        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);


    }

    //get single user

    @GetMapping("/{userId}")
    @Cacheable( key = "#userId",value = "USER1234")
    public User getUser(@PathVariable("userId") String userId) {
        return userDao.get(userId);
    }

    //find all
    @GetMapping
    public List<User> getAll() {

        Map<Object, Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        List<User> collect = values.stream().map(value -> (User) value).collect(Collectors.toList());
        return collect;

    }

    //delete  user
    @DeleteMapping("/{userId}")
    @CacheEvict( key = "#userId",value = "USER1234")
    public void deleteUser(@PathVariable String userId) {
        userDao.delete(userId);
    }

}
