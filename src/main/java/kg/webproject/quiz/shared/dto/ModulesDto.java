package kg.webproject.quiz.shared.dto;

import kg.webproject.quiz.io.entities.QuestionEntity;

import javax.persistence.*;
import java.util.Set;

public class ModulesDto {
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
