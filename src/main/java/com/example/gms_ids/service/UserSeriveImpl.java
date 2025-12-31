package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.SearchRequest;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSeriveImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;

    @Override
    public Page<User> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> search(SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return userRepository.searchUsers(
                request.getUserName(),
                request.getEmail(),
                request.getPhoneNo(),
                request.getDepartmentId(),
                request.getPositionCode(),
                request.getCompanyName(),
                pageable
        );
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User create(UserRequest userRequest) throws Exception {
        Optional<User> userByUserName = userRepository.findUserByUserName(userRequest.getUserName());
        if (userByUserName.isPresent()) {
            throw new Exception("đã tồn tại tên đăng nhập");
        }
        User userCreated = mapper.toEntity(userRequest);
        userRepository.save(userCreated);
        return userCreated;
    }

    @Override
    public User update(String id, UserRequest userRequest) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new Exception("Khong ton tai user");
        }
        User userUpdate = user.get();
        mapper.updateEntity(userUpdate, userRequest);
        userRepository.save(userUpdate);
        return userUpdate;
    }

    @Override
    public void delete(String id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new Exception("Khong ton tai user");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteMultiple(List<String> ids) throws Exception {
        if (ids == null || ids.isEmpty()) {
            throw new Exception("Danh sach id khong duoc rong");
        }
        List<User> users = userRepository.findAllById(ids);
        if (users.size() != ids.size()) {
            throw new Exception("Mot so user khong ton tai");
        }
        userRepository.deleteAllById(ids);
    }
}
