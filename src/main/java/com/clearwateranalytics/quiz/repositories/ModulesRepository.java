package com.clearwateranalytics.quiz.repositories;

import com.clearwateranalytics.quiz.pojo.entities.ModulesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ModulesRepository extends CrudRepository<ModulesEntity, Long> {
    ModulesEntity findById(long id);
    ModulesEntity findByName(String name);
    Set<ModulesEntity>  findAllByOrderByIdAsc();
}
