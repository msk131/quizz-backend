package kg.webproject.quiz.ui.models.response;

import kg.webproject.quiz.shared.dto.QuestionDto;

import java.util.Set;

public class ModuleResponseModel {
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
