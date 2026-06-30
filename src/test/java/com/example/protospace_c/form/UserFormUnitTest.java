package com.example.protospace_c.form;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.validation.BindingResult;

import com.example.protospace_c.factory.UserFormFactory;
import com.example.protospace_c.validation.ValidationPriority1;
import com.example.protospace_c.validation.ValidationPriority2;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class UserFormTest {

    private Validator validator;

    
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class 正常系のテスト {
        @Test
        void 全ての項目が正しく入力されていればエラーにならない() {
            UserForm userForm = UserFormFactory.createUser();
            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class, ValidationPriority2.class);
            assertThat(violations).isEmpty();
        }
    }

    @Nested
    class 異常系のテスト {

        @Test
        void nameが空では登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setName(""); 

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
            assertEquals(1, violations.size());
            assertEquals("name can't be blank", violations.iterator().next().getMessage());
        }

        @Test
        void emailが空では登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setEmail("");

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
            assertEquals(1, violations.size());
            assertEquals("Email can't be blank", violations.iterator().next().getMessage());
        }

        @Test
        void emailにアットマークが含まれていないと登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setEmail("example.com"); 

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
            assertEquals(1, violations.size());
            assertEquals("Invalid email format", violations.iterator().next().getMessage()); 
        }

        @Test
        void passwordが空では登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setPassword("");

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
            assertEquals(1, violations.size());
            assertEquals("password can't be blank", violations.iterator().next().getMessage());
        }

        @Test
        void passwordが5文字以下では登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setPassword("12345"); 

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
            assertEquals(1, violations.size());
            assertEquals("Password should be between 6 and 128 characters", violations.iterator().next().getMessage());
        }

        @Test
        void profileが空では登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setProfile("");

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
            assertEquals(1, violations.size());
        }

        @Test
        void affiliationが空では登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setAffiliation("");

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
            assertEquals(1, violations.size());
        }

        @Test
        void positionが空では登録できない() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setPosition("");

            Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
            assertEquals(1, violations.size());
        }
    }

    @Nested
    class カスタムバリデーションのテスト {
        
        @Test
        void passwordとpasswordConfirmationが一致しないとエラーが追加される() {
            UserForm userForm = UserFormFactory.createUser();
            userForm.setPassword("password123");
            userForm.setPasswordConfirmation("different456"); 
            
            BindingResult bindingResult = mock(BindingResult.class);
            userForm.validatePasswordConfirmation(bindingResult);
            verify(bindingResult, times(1)).rejectValue("passwordConfirmation", null, "Password confirmation does not match");
        }
    }
}