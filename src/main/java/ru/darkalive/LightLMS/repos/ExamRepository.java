package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Exam;
import ru.darkalive.LightLMS.entities.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Integer> {

    Exam findFirstById(int id);
    Exam findFirstBySubject(Subject subject);
}