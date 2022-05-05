package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserDAOHibernateImpl implements UserDAO {
    private static long ID_NUMBER;
    private List<User> userList;

    {
        userList = new ArrayList<>();

        userList.add(new User(++ID_NUMBER, "Ivan", "Pyhkin", (byte) 23));
        userList.add(new User(++ID_NUMBER, "Aleks", "Evdokim", (byte) 46));
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
       // return userList;
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class,id);
//        for (User user : userList) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        return null;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
//        userList.add(user);
//        user.setId(++ID_NUMBER);
    }

    public void editUser(long id, User user) {
        entityManager.merge(user);
//        User userOld = getUserById(id);
//        userOld.setName(user.getName());
//        userOld.setLastName(user.getLastName());
//        userOld.setAge(user.getAge());
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
//        int i = -1;
//        for (User user : userList) {
//            if (user.getId() == id) {
//                i = userList.indexOf(user);
//            }
//        }
//        userList.remove(i);
    }

}
