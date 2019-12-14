package com.company;

public class User {
    protected String name;
    protected String login;
    protected String password;

    public User() {name = ""; login = ""; password = "";}
    public User(String name, String login, String password){ this.name = name; this.login = login; this.password = password; }
    public boolean isAdmin() {return false;}
    public void getAnswer(Question question, int answer) {}
    public String getSuccess(){return name;}
    public boolean enter(String login, String password) {
        if(this.login.equals(login) && this.password.equals(password)) return true;
        return false;
    }
}
