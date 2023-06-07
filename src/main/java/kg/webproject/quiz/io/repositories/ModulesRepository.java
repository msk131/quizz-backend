package kg.webproject.quiz.io.repositories;

import kg.webproject.quiz.io.entities.ModulesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ModulesRepository extends CrudRepository<ModulesEntity, Long> {
    ModulesEntity findById(long id);
    ModulesEntity findByName(String name);
}
