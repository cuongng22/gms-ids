package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.UserRequest;
import com.example.gms_ids.table.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class UserSeriveImpl implements  UserService{
    private final ObjectMapper objectMapper;

    public UserSeriveImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User create(UserRequest user) {
        User userCreated = new User();
        userCreated = objectMapper.convertValue(user, User.class);
        return null;
    }

    @Override
    public User update(UserRequest user) {
        return null;
    }

    @Override
    public void delete(UserRequest user) {

    }
}
