package com.example.protospace_c.factory;

import com.github.javafaker.Faker;
import com.example.protospace_c.form.UserForm;
import java.util.Locale;

public class UserFormFactory {
    private static final Faker faker = new Faker(new Locale("ja"));

    public static UserForm createUser() {
        UserForm userForm = new UserForm();

        
        userForm.setName(faker.name().fullName()); 
        userForm.setEmail(faker.internet().safeEmailAddress());
        
        String password = faker.internet().password(6, 12);
        userForm.setPassword(password);
        userForm.setPasswordConfirmation(password);
        userForm.setProfile(faker.lorem().sentence());
        userForm.setAffiliation(faker.company().name());
        userForm.setPosition(faker.job().title());

        return userForm;
    }
}