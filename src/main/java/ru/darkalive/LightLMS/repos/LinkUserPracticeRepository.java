package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.*;

import java.util.List;

@Repository
public interface LinkUserPracticeRepository extends CrudRepository<LinkUserPractice, Integer> {

    LinkUserPractice findFirstById(int id);
    LinkUserPractice findFirstByUserAndPractice(User user, Practice practice);
}
