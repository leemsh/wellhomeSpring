package com.luke.wellhome.service;


import com.luke.wellhome.domain.UserEntity;
import com.luke.wellhome.dto.SuccessResponse;
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

    public SuccessResponse<String> joinProcess(UserEntity userEntity) {

        Boolean isExist = userRepository.existsByUsername(userEntity.getUsername());

        if (isExist) {
            return new SuccessResponse<>("already exist", 409);
        }

        // 패스워드가 null 또는 빈 값인지 확인
        if (userEntity.getPassword() == null || userEntity.getPassword().isEmpty()) {
            return new SuccessResponse<>("password is empty", 403);
        }

        // 패스워드를 인코딩해서 저장
        try {
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        }catch(Exception e) {
            return new SuccessResponse<>("save fail", 500);
        }
        userRepository.save(userEntity);
        return new SuccessResponse<>("success", 200);
    }
}
