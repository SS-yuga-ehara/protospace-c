package com.example.protospace_c.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PrototypeForm {
  @NotBlank(message = "Name can't be blank",groups = ValidationPriority1.class)
  private String name;

  @NotBlank(message = "Slogan can't be blank",groups = ValidationPriority1.class)
  private String slogan;

  @NotBlank(message = "Concept can't be blank",groups = ValidationPriority1.class)
  private String concept;

  @NotBlank(message = "Image can't be blank",groups = ValidationPriority1.class)
  private MultipartFile image;
}
