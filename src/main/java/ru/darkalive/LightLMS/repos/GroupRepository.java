package ru.darkalive.LightLMS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Group;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {

    public Group getFirstByName(String name);

    @Query(value = "SELECT name FROM subject_group")
    public List<String> getAll();
}
