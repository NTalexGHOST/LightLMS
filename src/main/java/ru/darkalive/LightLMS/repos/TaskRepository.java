package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Task;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    Task findFirstById(int id);
}
