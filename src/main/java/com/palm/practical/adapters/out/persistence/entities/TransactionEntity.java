package com.palm.practical.adapters.out.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long accountId;

  @Column(nullable = false)
  private Long amount;

  @Column(nullable = false)
  private String ttype;
}
