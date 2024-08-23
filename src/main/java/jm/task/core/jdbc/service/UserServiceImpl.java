package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl useDAO = new UserDaoJDBCImpl();

    public UserServiceImpl() {
    }

    public void createUsersTable() {
         useDAO.createUsersTable();
    }

    public void dropUsersTable() {
        useDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        useDAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        useDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return useDAO.getAllUsers();
    }

    public void cleanUsersTable() {
        useDAO.cleanUsersTable();
    }
}
