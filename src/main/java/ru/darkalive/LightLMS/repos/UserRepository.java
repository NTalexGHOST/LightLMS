package ru.darkalive.LightLMS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkalive.LightLMS.entities.Role;
import ru.darkalive.LightLMS.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findFirstByUserName(String userName);
    public List<User> findAll();
    public List<User> findAllByRole(Role role);
}
