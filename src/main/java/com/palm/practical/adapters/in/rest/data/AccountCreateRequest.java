package com.palm.practical.adapters.in.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountCreateRequest {

    @NotNull(message = "Field 'userId' cannot be empty.")
    private Long userId;

}
