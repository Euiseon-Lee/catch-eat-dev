package com.catcheat.api.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // 현재 로그인 시스템 없으므로 SYSTEM 고정
        // (나중에 Security 연동하면 여기서 username 반환)
        return Optional.of("SYSTEM");
    }
}