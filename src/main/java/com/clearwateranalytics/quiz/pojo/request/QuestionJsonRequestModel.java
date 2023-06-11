package com.clearwateranalytics.quiz.pojo.request;

import java.util.Set;

public class QuestionJsonRequestModel {

    private long Id;
    private String question;
    private String answer;
    private Set<String> options;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Set<String> getOptions() {
        return options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }
}