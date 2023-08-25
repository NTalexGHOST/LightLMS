package ru.darkalive.LightLMS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Practice;

import java.util.Optional;

@Repository
public interface PracticeRepository extends CrudRepository<Practice, Integer> {

    Practice findFirstById(int id);
    @Query("SELECT MAX(position) FROM resource_practice WHERE theme.id=?1")
    Optional<Integer> lastPositionByTheme(int themeId);
    @Query("SELECT MAX(position) FROM resource_practice WHERE exam.id=?1")
    Optional<Integer> lastPositionByExam(int examId);
}
