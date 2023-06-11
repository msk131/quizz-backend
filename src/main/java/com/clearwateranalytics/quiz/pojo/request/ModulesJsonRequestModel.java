package com.clearwateranalytics.quiz.pojo.request;

import java.util.Set;

public class ModulesJsonRequestModel implements Comparable<ModulesJsonRequestModel>{
    private long id;

    private String name;

    private Set<QuestionJsonRequestModel> questions;

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

    public Set<QuestionJsonRequestModel> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionJsonRequestModel> questions) {
        this.questions = questions;
    }

    @Override
    public int compareTo(ModulesJsonRequestModel moduleResponseModel) {

        long compareId = ((ModulesJsonRequestModel) moduleResponseModel).getId();
        return (int) (this.id - compareId);
    }
}
