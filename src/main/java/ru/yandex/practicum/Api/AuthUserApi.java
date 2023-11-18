package ru.yandex.practicum.Api;

public class AuthUserApi {
    private String email;
    private String password;

    public AuthUserApi(String mail, String pass) {
        this.email = mail;
        this.password = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
