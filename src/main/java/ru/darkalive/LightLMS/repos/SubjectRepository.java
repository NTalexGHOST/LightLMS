package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Subject;
import ru.darkalive.LightLMS.entities.User;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    List<Subject> findAllByTeacher(User user);
}
