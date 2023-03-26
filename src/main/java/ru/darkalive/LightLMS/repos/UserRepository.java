package ru.darkalive.LightLMS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Group;
import ru.darkalive.LightLMS.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public List<User> findAll();
    public List<User> findByGroup(Group group);

    public User findFirstByFullName(String fullName);
    public User findFirstByGroupAndFullName(Group group, String fullName);

    @Query(value="SELECT fullName FROM subject_user")
    public List<String> findUserFullNames();
    @Query(value="SELECT fullName FROM subject_user WHERE role.name=?1")
    public List<String> findUserFullNamesByRoleName(String roleName);
    @Query(value="SELECT fullName FROM subject_user WHERE role.name='Студент' AND group.name=?1")
    public List<String> findStudentFullNamesByGroup(String groupName);
}
