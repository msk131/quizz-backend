package com.clearwateranalytics.quiz.repositories;

import com.clearwateranalytics.quiz.pojo.entities.ModulesEntity;
import com.clearwateranalytics.quiz.pojo.entities.AnswerEntity;
import com.clearwateranalytics.quiz.pojo.entities.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {
    QuestionEntity findById(long id);
    QuestionEntity findByQuestionContent(String questionContent);
    AnswerEntity findByAnswers(String answerContent);
    QuestionEntity[] findAllByModule(ModulesEntity modulesEntity);
}
