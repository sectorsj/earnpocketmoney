package ru.coolteam.earnpocketmoney.models;

public abstract class UserEntity {
    String login;
    String password;

    public abstract String getLogin();

    public abstract void setLogin(String login);

    public abstract String getPassword();

    public abstract void setPassword(String password);
}
