package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.UserRequest;
import com.example.gms_ids.repository.UserRepository;
import com.example.gms_ids.table.User;
import com.example.gms_ids.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSeriveImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;

    @Override
    public Page<User> findAll (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User create(UserRequest userRequest) {
        try {
            User userCreated = mapper.toEntity(userRequest);
            userRepository.save(userCreated);
            return userCreated;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(UserRequest user) {
        return null;
    }

    @Override
    public void delete(UserRequest user) {

    }
}
