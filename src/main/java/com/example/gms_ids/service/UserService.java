package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.SearchRequest;
import com.example.gms_ids.dto.request.UserRequest;
import com.example.gms_ids.table.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> findAll(int page, int size);
    Page<User> search(SearchRequest request);
    User findByUsername(String username);
    User create(UserRequest user) throws Exception;
    User update(String id, UserRequest user) throws Exception;
    void delete(String id) throws Exception;
    void deleteMultiple(List<String> ids) throws Exception;
}
