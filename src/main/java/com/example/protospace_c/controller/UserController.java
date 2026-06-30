package com.example.protospace_c.controller; 

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.protospace_c.entity.UserEntity;
import com.example.protospace_c.form.UserForm;
import com.example.protospace_c.repository.UserRepository;
import com.example.protospace_c.service.UserService;
import com.example.protospace_c.validation.ValidationOrder;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    //新規登録画面を表示する
    @GetMapping("/users/sign_up")
    public String showSignUp(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/signUp";
    }

    //新規登録処理を行う
    @PostMapping("/users/sign_up") 
    public String createUser(@ModelAttribute("userForm") @Validated(ValidationOrder.class) UserForm userForm, BindingResult result, Model model) {
        
        userForm.validatePasswordConfirmation(result);
        
        if (userRepository.existsByEmail(userForm.getEmail())) {
            result.rejectValue("email", null, "This email address is already registered");
        }

        
        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("userForm", userForm);
            return "users/signUp";
        }
      
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userForm.getName()); 
        userEntity.setEmail(userForm.getEmail());
        userEntity.setPassword(userForm.getPassword());
        userEntity.setProfile(userForm.getProfile());       
        userEntity.setAffiliation(userForm.getAffiliation()); 
        userEntity.setPosition(userForm.getPosition());     

        try {
            userService.createUserWithEncryptedPassword(userEntity);
        } catch (Exception e) {
            System.out.println("エラー：" + e.getMessage());
            return "redirect:/users/sign_up";
        }
        return "redirect:/";
    }

    //ログイン画面を表示する
    @GetMapping("/users/login")
    public String showLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Incorrect email or password");
        }
        return "users/login";
    }
}