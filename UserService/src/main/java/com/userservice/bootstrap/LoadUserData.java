package com.userservice.bootstrap;

import com.userservice.entity.Authority;
import com.userservice.entity.User;
import com.userservice.repository.AuthorityRepository;
import com.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadUserData implements ApplicationRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        loadSecurityData();
    }

    private void loadSecurityData() {

        userRepository.deleteAll();
        authorityRepository.deleteAll();

        Authority admin = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
        Authority userRole = authorityRepository.save(Authority.builder().role("ROLE_USER").build());


        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .email("user@email.com")
                .authority(userRole)
                .build());

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@email.com")
                .authority(admin)
                .build());

        log.info("Users Loaded: " + userRepository.count());
    }
}
