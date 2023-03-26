package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    public Role findFirstByName(String name);
}
