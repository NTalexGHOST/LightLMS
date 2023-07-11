package ru.darkalive.LightLMS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.ExternalResource;

import java.util.Optional;

@Repository
public interface ExternalResourceRepository extends CrudRepository<ExternalResource, Integer> {

    ExternalResource findFirstById(int id);
    @Query("SELECT MAX(position) FROM resource_external WHERE theme.id=?1")
    Optional<Integer> lastPositionByTheme(int themeId);
    @Query("SELECT MAX(position) FROM resource_external WHERE task.id=?1")
    Optional<Integer> lastPositionByTask(int taskId);
    @Query("SELECT MAX(position) FROM resource_external WHERE exam.id=?1")
    Optional<Integer> lastPositionByExam(int examId);
}
