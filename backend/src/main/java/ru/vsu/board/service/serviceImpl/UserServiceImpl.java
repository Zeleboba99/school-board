package ru.vsu.board.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.board.dto.UserResponse;
import ru.vsu.board.model.User;
import ru.vsu.board.repository.UserRepository;
import ru.vsu.board.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> all = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        for(User user : all){
            UserResponse userResponse = new UserResponse();
            userResponse.setUser_id(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setPassword(user.getPassword());
            //костыль для получения роли
            userResponse.setRole(user.getRoles().get(0).getName().toString());
            responses.add(userResponse);
        }
        return responses;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
