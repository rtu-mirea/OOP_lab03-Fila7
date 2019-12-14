package com.company;

public class Admin extends User {
    Admin (String name, String login, String password) {super(name, login, password);}
    @Override
    public boolean isAdmin() {return true;}
}
