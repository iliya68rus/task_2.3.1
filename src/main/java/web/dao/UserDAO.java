package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUser();
    public User getUserById(long id);
    public void saveUser(User user);
    public void editUser(long id, User user);
}
