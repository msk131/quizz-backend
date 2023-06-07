package kg.webproject.quiz.io.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name="modules")
public class ModulesEntity implements Serializable {
    private static final long serialVersionUID = -5554052149941848167L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="my_module_seq_gen")
    @SequenceGenerator(name="my_module_seq_gen", sequenceName="MY_MOD_SEQ")
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<QuestionEntity> questions;

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

    public Set<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionEntity> questions) {
        this.questions = questions;
    }
}
