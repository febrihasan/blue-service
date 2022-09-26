package org.ait.project.blu.modules.auth.service.delegate.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.blu.modules.auth.model.entity.Users;
import org.ait.project.blu.modules.auth.model.repository.UserRepository;
import org.ait.project.blu.modules.auth.service.delegate.UserDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDelegateImpl implements UserDelegate {

    private final UserRepository userRepository;

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }


    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
