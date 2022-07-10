package com.palm.practical.adapters.in.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

  @NotEmpty(message = "Field 'email' cannot be empty.")
  private String email;

}
