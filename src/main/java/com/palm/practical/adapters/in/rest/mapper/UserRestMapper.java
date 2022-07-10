package com.palm.practical.adapters.in.rest.mapper;

import com.palm.practical.adapters.in.rest.data.UserCreateRequest;
import com.palm.practical.adapters.in.rest.data.UserCreateResponse;
import com.palm.practical.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    User toUser(UserCreateRequest userCreateRequest);
    UserCreateResponse toUserCreateResponse(User user);
}
