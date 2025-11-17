package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.UserRequest;
import com.example.gms_ids.table.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> findAll(int page, int size);
    User findByUsername(String username);
    User create(UserRequest user);
    User update(UserRequest user);
    void delete(UserRequest user);
}
