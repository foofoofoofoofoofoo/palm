package com.palm.practical.ports.in;

import com.palm.practical.domain.models.User;

public interface UserCreateUseCase {

  User createUser(User user);

}
