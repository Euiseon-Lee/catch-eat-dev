package com.catcheat.api.security.service;

import com.catcheat.api.security.entity.User;
import com.catcheat.api.security.repository.UserRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class UserServiceImpl implements IUserService {
    /** Service란?
     *  여러 DAO 또는 Repository를 조합하여 비즈니스 로직을 수행.
     *  예시: 데이터 검증, 변환, 가공, 트랜잭션 관리
     *
     *  비즈니스 로직이란 사용자의 요구사항을 처리하는 핵심 로직 == 데이터를 어떻게 가공하고 처리할 것인가를 결정
     */
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
