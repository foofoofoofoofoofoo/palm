package com.palm.practical.adapters.in.rest;

import com.palm.practical.adapters.in.rest.data.UserCreateRequest;
import com.palm.practical.adapters.in.rest.data.UserCreateResponse;
import com.palm.practical.adapters.in.rest.mapper.UserRestMapper;
import com.palm.practical.domain.models.User;
import com.palm.practical.ports.in.UserCreateUseCase;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class RestUserAdapter {

  private final UserCreateUseCase userCreateUseCase;
  private final UserRestMapper userRestMapper;

  @PostMapping(value = "/")
  public ResponseEntity<UserCreateResponse> createUser(
    @RequestBody @Valid UserCreateRequest userCreateRequest
  ) {
    final User user = userRestMapper.toUser(userCreateRequest);
    final User createdUser = userCreateUseCase.createUser(user);
    return new ResponseEntity<>(userRestMapper.toUserCreateResponse(createdUser), HttpStatus.CREATED);
  }

}
