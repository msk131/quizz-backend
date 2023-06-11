package com.clearwateranalytics.quiz.pojo.response;

import com.clearwateranalytics.quiz.pojo.dto.QuestionDto;

import java.util.Set;

public class ModuleResponseModel implements Comparable<ModuleResponseModel>{

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
    @Override
    public int compareTo(ModuleResponseModel moduleResponseModel) {

        long compareId = ((ModuleResponseModel) moduleResponseModel).getId();
        return (int) (this.id - compareId);
    }
}
