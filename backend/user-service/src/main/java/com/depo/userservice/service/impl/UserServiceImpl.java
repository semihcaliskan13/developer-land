package com.depo.userservice.service.impl;

import com.depo.userservice.entity.User;
import com.depo.userservice.exception.UserNotFoundException;
import com.depo.userservice.repository.UserRepository;
import com.depo.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("userServiceImpl")
@ConditionalOnProperty(name = "data.source.location", havingValue = "db")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(String id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllByIds(List<String> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User update(User user){
        User userResponse = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        if (user.getPassword()==null){
            user.setPassword(userResponse.getPassword());
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByUsername(String username) {
       return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
