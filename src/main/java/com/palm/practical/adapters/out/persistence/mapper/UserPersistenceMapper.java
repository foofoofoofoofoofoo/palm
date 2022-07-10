package com.palm.practical.adapters.out.persistence.mapper;

import com.palm.practical.adapters.out.persistence.entities.UserEntity;
import com.palm.practical.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
