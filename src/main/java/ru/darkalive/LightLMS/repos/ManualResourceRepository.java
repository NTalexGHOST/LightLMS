package ru.darkalive.LightLMS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.ManualResource;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManualResourceRepository extends CrudRepository<ManualResource, Integer> {

    ManualResource findFirstById(int id);
    @Query("SELECT MAX(position) FROM resource_manual WHERE theme.id=?1")
    Optional<Integer> lastPositionByTheme(int themeId);

    @Query("SELECT MAX(position) FROM resource_manual WHERE task.id=?1")
    Optional<Integer> lastPositionByTask(int taskId);

    @Query("SELECT MAX(position) FROM resource_manual WHERE exam.id=?1")
    Optional<Integer> lastPositionByExam(int examId);
}
