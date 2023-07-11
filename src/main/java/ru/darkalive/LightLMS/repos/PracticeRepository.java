package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Practice;

@Repository
public interface PracticeRepository extends CrudRepository<Practice, Integer> {

    Practice findFirstById(int id);
}
