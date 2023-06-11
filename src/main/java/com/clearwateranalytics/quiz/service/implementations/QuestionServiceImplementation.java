package com.clearwateranalytics.quiz.service.implementations;

import com.clearwateranalytics.quiz.pojo.dto.AnswerDto;
import com.clearwateranalytics.quiz.pojo.dto.ModulesDto;
import com.clearwateranalytics.quiz.pojo.dto.QuestionDto;
import com.clearwateranalytics.quiz.pojo.entities.ModulesEntity;
import com.clearwateranalytics.quiz.pojo.entities.QuestionEntity;
import com.clearwateranalytics.quiz.repositories.ModulesRepository;
import com.clearwateranalytics.quiz.repositories.QuestionRepository;
import com.clearwateranalytics.quiz.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    private QuestionRepository _questionRepository;

    @Autowired
    private ModulesRepository _modulesRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public QuestionServiceImplementation(QuestionRepository questionRepository){
        _questionRepository = questionRepository;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto question) {

        if(_questionRepository.findByQuestionContent(question.getQuestionContent()) != null)
            throw new RuntimeException("Question already exists in Database");
        
        for(AnswerDto answer: question.getAnswers()){
            answer.setQuestion(question);
        }

        QuestionEntity questionEntity = modelMapper.map(question, QuestionEntity.class);
        QuestionDto returnValue = modelMapper.map(_questionRepository.save(questionEntity), QuestionDto.class);

        return returnValue;
    }

    @Override
    public QuestionDto getQuestionById(long id) {

        return modelMapper.map(_questionRepository.findById(id), QuestionDto.class);
    }


    @Override
    public String deleteQuestionById(long id) {
        _questionRepository.deleteById(id);

        return "Operation successful";
    }

    @Override
    public ModulesDto createModules(ModulesDto modulesDto) {

        ModulesDto returnValue= null;
        if(modulesDto.getName() != null)
         if(_modulesRepository.findByName(modulesDto.getName()) != null)
            throw new RuntimeException("Module already exists in Database");
         if(modulesDto.getId() != 0 && modulesDto.getQuestions()!=null){

             modulesDto.setName(_modulesRepository.findById(modulesDto.getId()).getName());
         }

        if(modulesDto.getQuestions()!=null){
            for (QuestionDto questionDto:modulesDto.getQuestions() ) {

                questionDto.setModule(modulesDto);
                for (AnswerDto answer : questionDto.getAnswers()) {
                    answer.setQuestion(questionDto);
                }
            }
        }

        ModulesEntity modulesEntity = modelMapper.map(modulesDto, ModulesEntity.class);
        returnValue = modelMapper.map(_modulesRepository.save(modulesEntity), ModulesDto.class);
        return returnValue;
    }

    @Override
    public ModulesDto getModuleById(long id) {

        return modelMapper.map(_modulesRepository.findById(id), ModulesDto.class);
    }

    @Override
    public Set<ModulesDto> getAllModules() {

        Set<ModulesDto> returnSet = new HashSet<>();
        for(ModulesEntity modulesEntity:_modulesRepository.findAllByOrderByIdAsc()){
            modulesEntity.setQuestions(null);
            ModulesDto modulesDto = modelMapper.map(modulesEntity, ModulesDto.class);
            returnSet.add(modulesDto);
        }
        return returnSet;
    }

    @Override
    public String deleteModuleById(long id) {

        _modulesRepository.deleteById(id);

        return "Operation successful";
    }

    @Override
    public ModulesDto updateModule(QuestionDto questionToAdd, long id) {

        ModulesEntity modulesEntity = _modulesRepository.findById(id);
        ModulesDto modulesDto = modelMapper.map(modulesEntity, ModulesDto.class);
        modulesDto.getQuestions().add(questionToAdd);
        ModulesEntity modulesEntityToReturn = _modulesRepository.save(modelMapper.map(modulesDto, ModulesEntity.class));

        return modelMapper.map(modulesEntityToReturn, ModulesDto.class);
    }

//    @Override
//    public QuestionDto updateQuestion(long Id, QuestionDto question) {
////        QuestionDto questionUpdate = modelMapper.map(question, QuestionDto.class);
////
////        return modelMapper.map(_questionRepository.findById(Id), (Type) QuestionResponseModel.class);
//        QuestionDto returnValue = new QuestionDto();
//
//        QuestionEntity questionEntity = _questionRepository.findById(Id);
//
//        questionEntity.setQuestionContent(question.getQuestionContent());
////        questionEntity.setAnswers(question.getAnswers());
//
//        QuestionEntity updatedQuestionDetails = _questionRepository.save(questionEntity);
//        BeanUtils.copyProperties(updatedQuestionDetails, returnValue);
//
//        return returnValue;
//    }

    @Override
    public Set<QuestionDto> getAllQuestions(long id) {
        Set<QuestionDto> returnSet = new HashSet<>();

        for(QuestionEntity question: _questionRepository.findAllByModule(_modulesRepository.findById(id))){
            QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
            returnSet.add(questionDto);
        }
        return returnSet;
    }

    @Override
    public QuestionDto updateAnswer(AnswerDto answer, long id) {
        QuestionEntity question = _questionRepository.findById(id);
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.getAnswers().add(answer);
        QuestionEntity returnQuestion = _questionRepository.save(modelMapper.map(questionDto, QuestionEntity.class));

        return modelMapper.map(returnQuestion, QuestionDto.class);
    }
}
