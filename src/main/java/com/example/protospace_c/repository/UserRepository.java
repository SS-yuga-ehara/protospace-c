package com.example.protospace_c.repository; 

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.protospace_c.entity.UserEntity;

@Mapper
public interface UserRepository {

    //ログイン時
    @Select("SELECT * FROM users WHERE email = #{email}")
    UserEntity findByEmail(String email);

    // メールアドレスが既に存在するか
    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE email = #{email})")
    boolean existsByEmail(String email);

    //新規登録
    @Insert("INSERT INTO users (name, email, password, profile, affiliation, position) " +
            "VALUES (#{name}, #{email}, #{password}, #{profile}, #{affiliation}, #{position})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(UserEntity user);
}