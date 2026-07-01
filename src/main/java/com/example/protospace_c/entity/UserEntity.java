package com.example.protospace_c.entity;

import lombok.Data;

@Data
public class UserEntity {
  private Integer id;
  private String name;
  private String password;
  private String email;
  private String profile;
  private String affiliation;
  private String position;
}
