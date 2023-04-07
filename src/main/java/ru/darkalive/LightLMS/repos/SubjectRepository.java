package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {


}
