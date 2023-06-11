package com.clearwateranalytics.quiz.controllers;

import com.clearwateranalytics.quiz.pojo.dto.AnswerDto;
import com.clearwateranalytics.quiz.pojo.request.*;
import com.clearwateranalytics.quiz.pojo.response.AnswerResponseModel;
import com.clearwateranalytics.quiz.service.MailService;
import com.clearwateranalytics.quiz.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import com.clearwateranalytics.quiz.pojo.dto.ModulesDto;
import com.clearwateranalytics.quiz.pojo.dto.QuestionDto;
import com.clearwateranalytics.quiz.pojo.response.ModuleResponseModel;
import com.clearwateranalytics.quiz.pojo.response.QuestionResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("module")
public class ModuleController {

    @Autowired
    QuestionService questionService;

    @Autowired
    private MailService mailService;
    private ModelMapper modelMapper = new ModelMapper();

    @CrossOrigin
    @PostMapping(path = "/mail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "mail")
    public ResponseEntity<String> sendMail(@RequestParam("file") MultipartFile file, @ModelAttribute MailRequest request) {

        File convFile = new File( file.getOriginalFilename() );
        try{
            FileOutputStream fos = new FileOutputStream( convFile );
            fos.write( file.getBytes() );
            fos.close();
        }catch (IOException e){

            System.out.println(e.getMessage());
        }

        Map<String, String> model = new HashMap<>();
        model.put("name", request.getName());
        model.put("body", request.getBody());
        model.put("value", "Greetings from BrainTeaser!");
        String response = mailService.sendMail(request, model, convFile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    @ApiOperation(value = "createModule")
    public ModuleResponseModel createModule(@RequestBody ModuleRequestModel module) {
        ModulesDto modulesDto = modelMapper.map(module, ModulesDto.class);

        return modelMapper.map(questionService.createModules(modulesDto), ModuleResponseModel.class);

    }

    @CrossOrigin
    @PostMapping(path = "/json")
    @ApiOperation(value = "createJsonModule")
    public ModuleResponseModel createJsonModule(@RequestBody ModulesJsonRequestModel modulesJsonRequestModel) {

        ModuleRequestModel module= new ModuleRequestModel();
        module.setId(modulesJsonRequestModel.getId());

        Set<QuestionDto> setQuestionDto = new HashSet<>();
        for(QuestionJsonRequestModel questionJsonRequestModel:modulesJsonRequestModel.getQuestions()){

            QuestionDto questionRequestModel= new QuestionDto();
            questionRequestModel.setQuestionContent(questionJsonRequestModel.getQuestion());
            Set<AnswerDto> set = new HashSet<>();
            for (String answer:questionJsonRequestModel.getOptions()){

                AnswerDto answerRequestModel= new AnswerDto();
                answerRequestModel.setAnswerContent(answer);
                if(answer.equals(questionJsonRequestModel.getAnswer())){

                    answerRequestModel.setCorrect(true);
                }else answerRequestModel.setCorrect(false);

                set.add(answerRequestModel);
            }
            questionRequestModel.setAnswers(set);
            setQuestionDto.add(questionRequestModel);
        }
        module.setQuestions(setQuestionDto);
        ModulesDto modulesDto = modelMapper.map(module, ModulesDto.class);
        return modelMapper.map(questionService.createModules(modulesDto), ModuleResponseModel.class);
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
        Set<ModuleResponseModel> returnValue = new TreeSet<>();
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