package kg.webproject.quiz.ui.controllers;

import io.swagger.annotations.ApiOperation;
import kg.webproject.quiz.service.serviceInterfaces.QuestionService;
import kg.webproject.quiz.shared.dto.AnswerDto;
import kg.webproject.quiz.shared.dto.ModulesDto;
import kg.webproject.quiz.shared.dto.QuestionDto;
import kg.webproject.quiz.ui.models.request.AnswerRequestModel;
import kg.webproject.quiz.ui.models.request.ModuleRequestModel;
import kg.webproject.quiz.ui.models.request.QuestionRequestModel;
import kg.webproject.quiz.ui.models.response.ModuleResponseModel;
import kg.webproject.quiz.ui.models.response.QuestionResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("module") // http:localhost:8080/module
public class ModuleController {

    @Autowired
    QuestionService questionService;

    private ModelMapper modelMapper = new ModelMapper();

    @CrossOrigin
    @PostMapping
    @ApiOperation(value = "createModule")
    public QuestionResponseModel createModule(@RequestBody QuestionRequestModel question) {
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);

        return modelMapper.map(questionService.createQuestion(questionDto), QuestionResponseModel.class);

    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getModuleId")
    public ModuleResponseModel getModuleId(@PathVariable long id) {
        return modelMapper.map(questionService.getModuleById(id), ModuleResponseModel.class);
    }

    @CrossOrigin
    @GetMapping
    @ApiOperation(value = "getAllModule")
    public Set<ModuleResponseModel> getAllModules() {
        Set<ModuleResponseModel> returnValue = new HashSet<>();
        for (ModulesDto moduleDto : questionService.getAllModules()) {
            ModuleResponseModel questionResponseModel = modelMapper.map(moduleDto, ModuleResponseModel.class);
            returnValue.add(questionResponseModel);
        }

        return returnValue;
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "deleteModuleById")
    public String deleteModuleById(@PathVariable long id) {

        questionService.deleteModuleById(id);
        return "Module was successfully deleted";

    }

    @CrossOrigin
    @PutMapping(path = "/{id}")
    @ApiOperation(value = "addQuestionToModule")
    public ModuleResponseModel addQuestionToModule(@PathVariable long id, @RequestBody QuestionRequestModel question) {

        QuestionDto questionToAdd = modelMapper.map(question, QuestionDto.class);

        return modelMapper.map(questionService.updateModule(questionToAdd, id), ModuleResponseModel.class);
    }

    @CrossOrigin
    @GetMapping(path = "/{id}/questions")
    @ApiOperation(value = "getAllQuestionsForModule")
    public Set<QuestionResponseModel> getAllQuestionsForModule(@PathVariable long id){
        Set<QuestionResponseModel> returnValue = new HashSet<>();
        for(QuestionDto questionDto: questionService.getAllQuestions(id)){
            QuestionResponseModel questionResponseModel= modelMapper.map(questionDto, QuestionResponseModel.class);
            returnValue.add(questionResponseModel);
        }

        return returnValue;
    }
}