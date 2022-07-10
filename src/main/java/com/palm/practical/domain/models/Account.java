package com.palm.practical.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  private Long id;
  private Long userId;

  @Builder.Default
  private Long balance = Long.valueOf(0L);

}
