package com.clearwateranalytics.quiz.pojo.request;

import com.clearwateranalytics.quiz.pojo.dto.QuestionDto;

import java.util.Set;

public class ModuleRequestModel {
    private long id;

    private String name;

    private Set<QuestionDto> questions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDto> questions) {
        this.questions = questions;
    }
}
