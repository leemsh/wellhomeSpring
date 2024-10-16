package com.luke.wellhome.service;

import com.luke.wellhome.domain.UserEntity;
import com.luke.wellhome.dto.SuccessResponse;
import com.luke.wellhome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SuccessResponse<UserEntity> getUser(String email){
        UserEntity user = userRepository.findByUsername(email);
        return new SuccessResponse<>(user, 200);
    }

    public SuccessResponse<String> updatePrice(UserEntity user){
        UserEntity userData = userRepository.findByUsername(user.getUsername());
        userData.setPrice(user.getPrice());
        userRepository.save(userData);
        return new SuccessResponse<>("update Success", 200);
    }
}
