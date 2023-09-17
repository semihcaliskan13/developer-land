package com.depo.userservice.service;

import com.depo.userservice.entity.User;

import java.util.List;

public interface UserService {
    User getById(String id);
    List<User> getAll();

    List<User> getAllByIds(List<String> ids);
    User save(User user);
    User update(User user);
    void delete(String id);
    User getByUsername(String username);
}
