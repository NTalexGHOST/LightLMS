package ru.darkalive.LightLMS.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Group;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {

    Group findFirstById(int id);
    List<Group> findAll(Sort sort);
}
