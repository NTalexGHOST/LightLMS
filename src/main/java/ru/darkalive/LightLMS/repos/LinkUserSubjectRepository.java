package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.LinkUserSubject;
import ru.darkalive.LightLMS.entities.Role;
import ru.darkalive.LightLMS.entities.Subject;
import ru.darkalive.LightLMS.entities.User;

import java.util.List;

@Repository
public interface LinkUserSubjectRepository extends CrudRepository<LinkUserSubject, Integer> {

    List<LinkUserSubject> findAllByUser(User user);
    List<LinkUserSubject> findAllByUserAndRole(User user, Role role);
    int countByUserAndSubjectAndRole(User user, Subject subject, Role role);
    int countByUserAndSubject(User user, Subject subject);
}
