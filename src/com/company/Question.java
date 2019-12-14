package com.company;

public class Question {
    private String text;
    private String[] answers;
    private int rightAnswer;

    public Question(String text, String[] answers, int right) {this.text = text; for(int i = 0; i < answers.length; answers[i] = Integer.toString(i+1) + " - " + answers[i], i++); this.answers = answers; rightAnswer = right;}
    public String getText() {return text;}
    public String[] getAnswers() {return answers;}
    public boolean isCorrect(int answer) {return answer == rightAnswer;}
}
