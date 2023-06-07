package kg.webproject.quiz.io.repositories;

import kg.webproject.quiz.io.entities.AnswerEntity;
import kg.webproject.quiz.io.entities.ModulesEntity;
import kg.webproject.quiz.io.entities.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {
    QuestionEntity findById(long id);
    QuestionEntity findByQuestionContent(String questionContent);
    AnswerEntity findByAnswers(String answerContent);
    QuestionEntity[] findAllByModule(ModulesEntity modulesEntity);
}
