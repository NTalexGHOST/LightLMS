package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.EducationType;

import java.util.List;

@Repository
public interface EducationTypeRepository extends CrudRepository<EducationType, Integer> {

    EducationType findFirstById(int id);
    List<EducationType> findAll();
}
