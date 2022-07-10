package com.palm.practical.ports.out;

import com.palm.practical.domain.models.User;

public interface UserOutputPort {
    User saveUser(User user);
}
