package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestedUser extends User {
    private int questionsCount = 0;
    private int rightAnswers = 0;

    public TestedUser(String name, String login, String password) {super(name, login, password);}
    @Override
    public void getAnswer(Question question, int answer)
    {
        if (question.isCorrect(answer)) rightAnswers++;
        questionsCount ++;
    }
    @Override
    public String getSuccess() {return super.name + " - " + Integer.toString((int)((float)rightAnswers / questionsCount*100));}
    public void clear() {questionsCount = 0; rightAnswers = 0;}
}
