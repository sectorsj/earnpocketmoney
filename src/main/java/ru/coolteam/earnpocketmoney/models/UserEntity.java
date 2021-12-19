package ru.coolteam.earnpocketmoney.models;

public interface UserEntity {
    /*String login;
    String password;
    Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public abstract String getLogin();

    public abstract void setLogin(String login);

    public abstract String getPassword();

    public abstract void setPassword(String password);*/

    public Role getRole();

    public void setRole(Role role);

    public  String getLogin();

    public  void setLogin(String login);

    public  String getPassword();

    public void setPassword(String password);





}
