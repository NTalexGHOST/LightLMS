package ru.darkalive.LightLMS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.darkalive.LightLMS.entities.Subject;
import ru.darkalive.LightLMS.entities.Theme;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeRepository extends CrudRepository<Theme, Integer> {

    Theme findFirstById(int id);
    List<Theme> findAllBySubjectOrderByPosition(Subject subject);
    @Query("SELECT MAX(position) FROM subject_theme WHERE subject.id=?1")
    Optional<Integer> lastPosition(int subjectId);
}
