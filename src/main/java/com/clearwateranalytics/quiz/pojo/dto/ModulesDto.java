package com.clearwateranalytics.quiz.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class ModulesDto implements Comparable<ModulesDto>{
    private long id;

    private String name;

    @JsonIgnore
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

    @Override
    public int compareTo(ModulesDto moduleResponseModel) {

        long compareId = ((ModulesDto) moduleResponseModel).getId();
        return (int) (this.id - compareId);
    }
}
