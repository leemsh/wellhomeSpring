package com.luke.wellhome.service;


import com.luke.wellhome.domain.UserEntity;
import com.luke.wellhome.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(UserEntity userEntity) {

        Boolean isExist = userRepository.existsByUsername(userEntity.getUsername());

        if (isExist) {
            return;
        }

        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);
    }
}
