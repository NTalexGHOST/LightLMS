package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.ResourceType;

@Repository
public interface ResourceTypeRepository extends CrudRepository<ResourceType, Integer> {

    ResourceType findFirstById(int id);
}
