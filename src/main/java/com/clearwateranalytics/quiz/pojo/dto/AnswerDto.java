package com.clearwateranalytics.quiz.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AnswerDto {

    private long Id;
    private String answerContent;
    private boolean correct;
    @JsonIgnore
    private QuestionDto question;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }
}
