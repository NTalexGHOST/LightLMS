package ru.darkalive.LightLMS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.ExternalResource;

@Repository
public interface ExternalResourceRepository extends CrudRepository<ExternalResource, Integer> {

    ExternalResource findFirstById(int id);
    @Query("SELECT MAX(position) FROM resource_external WHERE theme.id=?1")
    int lastPositionByTheme(int themeId);
}
