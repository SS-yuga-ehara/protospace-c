package com.example.protospace_c.form;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;

import com.example.protospace_c.validation.ValidationPriority1;
import com.example.protospace_c.validation.ValidationPriority2;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForm {

  @NotBlank(message="Email can't be blank",groups=ValidationPriority1.class)
  @Email(message = "Invalid email format",groups=ValidationPriority2.class)
  private String email;

  @NotBlank(message="password can't be blank",groups=ValidationPriority1.class)
  @Length(min=6,max=128,message="Password should be between 6 and 128 characters",groups=ValidationPriority2.class)
  private String password;

  private String passwordConfirmation;

  @NotBlank(message="name can't be blank",groups=ValidationPriority1.class)
  private String name;

  @NotBlank(message="profile can't be blank",groups=ValidationPriority1.class)
  private String profile;

  @NotBlank(message="affiliation can't be blank",groups=ValidationPriority1.class)
  private String affiliation;

  @NotBlank(message="position can't be blank",groups=ValidationPriority1.class)
  private String position;

  //passwordとpasswordConfirmationの値が一致しているか
  public void validatePasswordConfirmation(BindingResult result){
    if(!password.equals(passwordConfirmation)){
      result.rejectValue("passwordConfirmation",null,"Password confirmation does not match");
    }
  }
}
