package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOHibernateImpl implements UserDAO {
    private static long ID_NUMBER;
    private List<User> userList;

    {
        userList = new ArrayList<>();

        userList.add(new User(++ID_NUMBER, "Ivan", "Pyhkin", (byte) 23));
        userList.add(new User(++ID_NUMBER, "Aleks", "Evdokim", (byte) 46));
    }

    public List<User> getAllUser() {
        return userList;
    }

    @Override
    public User getUserById(long id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        userList.add(user);
        user.setId(++ID_NUMBER);
    }

    public void editUser(long id, User user) {
        User userOld = getUserById(id);
        userOld.setName(user.getName());
        userOld.setLastName(user.getLastName());
        userOld.setAge(user.getAge());
    }

    @Override
    public void deleteUser(long id) {
        int i = -1;
        for (User user : userList) {
            if (user.getId() == id) {
                i = userList.indexOf(user);
            }
        }
        userList.remove(i);
    }

}
