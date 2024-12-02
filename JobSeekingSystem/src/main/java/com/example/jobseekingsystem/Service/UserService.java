package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    // 1. Declare a dependency for UserRepository using Dependency Injection
    private final UserRepository userRepository;

    // 2. CRUD
    // 2.1 Get
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2.2 Post
    public void addUser(User user) {
        userRepository.save(user);
    }

    // 2.3 Update
    public Boolean updateUser(Integer id, User user) {
        // 1. To check if the row to be updated exists
        User oldUser = userRepository.getReferenceById(id);

        if (oldUser == null) {
            return false;
        }
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
        return true;
    }

    // 2.4 Delete
    public Boolean deleteUser(Integer id) {
        // 1. To check if the row to be deleted exists
        User oldUser = userRepository.getReferenceById(id);

        if (oldUser == null) {
            return false;
        }
        userRepository.delete(oldUser);
        return true;
    }
}