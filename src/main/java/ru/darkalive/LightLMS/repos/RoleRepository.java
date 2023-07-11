package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Role;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findFirstById(int id);
    List<Role> findAll();
}
