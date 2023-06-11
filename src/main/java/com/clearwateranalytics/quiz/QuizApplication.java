package com.clearwateranalytics.quiz;

import com.clearwateranalytics.quiz.pojo.entities.AnswerEntity;
import com.clearwateranalytics.quiz.service.QuestionService;
import com.clearwateranalytics.quiz.pojo.dto.AnswerDto;
import com.clearwateranalytics.quiz.pojo.dto.ModulesDto;
import com.clearwateranalytics.quiz.pojo.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EntityScan("com.clearwateranalytics.quiz.pojo.entities")
public class QuizApplication {

    @Autowired
    QuestionService questionService;
    public static void main(String[] args) {

        SpringApplication.run(QuizApplication.class, args);
    }

    @Bean
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void insertDummyAfterStartup() {


        ModulesDto modulesDto=new ModulesDto();
        modulesDto.setName("Core Java");

        Set quesions = new HashSet<QuestionDto>();

        QuestionDto questionEntity = new QuestionDto();
        questionEntity.setQuestionContent("Is Java platform independent platform");

        AnswerDto answerEntity1= new AnswerDto();
        answerEntity1.setAnswerContent("yes");
        answerEntity1.setCorrect(true);
        AnswerDto answerEntity2= new AnswerDto();
        answerEntity2.setAnswerContent("No");

        Set answers = new HashSet<AnswerEntity>();
        answers.add(answerEntity1);
        answers.add(answerEntity2);
        questionEntity.setAnswers(answers);

        quesions.add(questionEntity);
        modulesDto.setQuestions(quesions);

        questionService.createModules(modulesDto);

        ModulesDto modulesDto1=new ModulesDto();
        modulesDto1.setName("Python");

        questionService.createModules(modulesDto1);

    }

}
