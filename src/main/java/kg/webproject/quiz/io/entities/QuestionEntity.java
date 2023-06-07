package kg.webproject.quiz.io.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name="questions")
public class QuestionEntity implements Serializable {     //for persisting an object from memory to a sequence of bits
    private static final long serialVersionUID = -4664052149941848167L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="my_QUS_seq_gen")
    @SequenceGenerator(name="my_QUS_seq_gen", sequenceName="MY_QUS_SEQ")
    private long id;

    @Column(nullable = false)
    private String questionContent;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<AnswerEntity> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="modules_id")
    private ModulesEntity module;

    public ModulesEntity getModule() {
        return module;
    }

    public void setModule(ModulesEntity module) {
        this.module = module;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Set<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerEntity> answers) {
        this.answers = answers;
    }
}
