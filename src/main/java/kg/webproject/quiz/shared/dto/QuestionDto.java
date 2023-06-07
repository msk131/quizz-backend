package kg.webproject.quiz.shared.dto;

import kg.webproject.quiz.io.entities.ModulesEntity;

import java.util.Set;

public class QuestionDto {

    private long Id;
    private String questionContent;
    private Set<AnswerDto> answers;

    private ModulesDto module;

    public ModulesDto getModule() {
        return module;
    }

    public void setModule(ModulesDto module) {
        this.module = module;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Set<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDto> answers) {
        this.answers = answers;
    }
}
