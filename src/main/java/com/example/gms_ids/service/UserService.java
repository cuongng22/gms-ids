package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.UserRequest;
import com.example.gms_ids.table.User;

public interface UserService {
    User findByUsername(String username);
    User create(UserRequest user);
    User update(UserRequest user);
    void delete(UserRequest user);
}
