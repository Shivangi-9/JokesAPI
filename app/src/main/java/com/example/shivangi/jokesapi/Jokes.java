package com.example.shivangi.jokesapi;

public class Jokes {

    private String question;
    private String answer;


    public Jokes(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
