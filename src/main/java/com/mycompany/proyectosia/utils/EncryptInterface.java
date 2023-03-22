package com.mycompany.proyectosia.utils;

public interface EncryptInterface {
    String hashPassword(String password);
    boolean unHashPassword(String password, String hashPassword);
}
