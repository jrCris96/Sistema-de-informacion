package com.mycompany.proyectosia.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptPassword implements EncryptInterface {
    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean unHashPassword(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
