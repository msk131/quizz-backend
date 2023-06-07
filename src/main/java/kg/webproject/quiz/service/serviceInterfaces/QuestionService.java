package kg.webproject.quiz.service.serviceInterfaces;

import kg.webproject.quiz.shared.dto.AnswerDto;
import kg.webproject.quiz.shared.dto.ModulesDto;
import kg.webproject.quiz.shared.dto.QuestionDto;

import java.util.Set;

public interface QuestionService {

    QuestionDto createQuestion(QuestionDto questionDto);
    Set<QuestionDto> getAllQuestions(long id);
    QuestionDto getQuestionById(long id);
    QuestionDto updateAnswer(AnswerDto answerDto, long id);
    String deleteQuestionById(long id);

    ModulesDto createModules(ModulesDto modulesDto);

    ModulesDto getModuleById(long id);

    Set<ModulesDto> getAllModules();

    String deleteModuleById(long id);

    ModulesDto updateModule(QuestionDto questionToAdd, long id);
}
