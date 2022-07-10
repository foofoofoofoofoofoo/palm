package com.palm.practical.domain.services;

import com.palm.practical.domain.models.User;
import com.palm.practical.ports.in.UserCreateUseCase;
import com.palm.practical.ports.out.UserOutputPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements UserCreateUseCase {

    private final UserOutputPort userOutputPort;

    @Override
    public User createUser(User user) {
        final User createdUser = userOutputPort.saveUser(user);
        return createdUser;
    }
}
