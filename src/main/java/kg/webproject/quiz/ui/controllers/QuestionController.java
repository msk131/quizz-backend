package kg.webproject.quiz.ui.controllers;

import io.swagger.annotations.ApiOperation;
import kg.webproject.quiz.service.serviceInterfaces.QuestionService;
import kg.webproject.quiz.shared.dto.AnswerDto;
import kg.webproject.quiz.shared.dto.QuestionDto;
import kg.webproject.quiz.ui.models.request.AnswerRequestModel;
import kg.webproject.quiz.ui.models.request.QuestionRequestModel;
import kg.webproject.quiz.ui.models.response.QuestionResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("questions") // http:localhost:8080/questions
public class QuestionController {

    @Autowired
    QuestionService questionService;

    private ModelMapper modelMapper = new ModelMapper();

    @CrossOrigin
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getQuestionById")
    public QuestionResponseModel getQuestionById(@PathVariable long id){
        return modelMapper.map(questionService.getQuestionById(id), QuestionResponseModel.class);
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "deleteQuestionById")
    public String deleteQuestionById(@PathVariable long id){

        questionService.deleteQuestionById(id);
        return "Question was successfully deleted";

    }

    @CrossOrigin
    @PutMapping(path = "/{id}")
    @ApiOperation(value = "addAnswerToQuestion")
    public QuestionResponseModel addAnswerToQuestion(@PathVariable long id, @RequestBody AnswerRequestModel answer){

        AnswerDto answerToAdd = modelMapper.map(answer, AnswerDto.class);

        return modelMapper.map(questionService.updateAnswer(answerToAdd, id), QuestionResponseModel.class);

    }

//    @CrossOrigin
//    @PutMapping(path = "/{id}")
//    @ApiOperation(value = "updateQuestion")
//    public QuestionResponseModel updateQuestion(@PathVariable long id, @RequestBody QuestionRequestModel question){
//
//        QuestionDto questionToAdd = modelMapper.map(question, QuestionDto.class);
//
//        return modelMapper.map(questionService.updateQuestion(questionToAdd, id), QuestionResponseModel.class);
//    }


}
