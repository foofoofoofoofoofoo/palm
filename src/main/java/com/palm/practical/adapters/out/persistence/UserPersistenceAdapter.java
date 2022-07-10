package com.palm.practical.adapters.out.persistence;

import com.palm.practical.adapters.out.persistence.entities.UserEntity;
import com.palm.practical.adapters.out.persistence.mapper.UserPersistenceMapper;
import com.palm.practical.adapters.out.persistence.repositories.UserRepository;
import com.palm.practical.domain.models.User;
import com.palm.practical.ports.out.UserOutputPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

  private final UserRepository userRepository;
  private final UserPersistenceMapper userPersistenceMapper;

  @Override
  public User saveUser(User user) {
    final UserEntity userEntity = userPersistenceMapper.toUserEntity(user);
    return userPersistenceMapper.toUser(userRepository.save(userEntity));
  }
}
