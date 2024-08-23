package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;


public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection = Util.getConnection();
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static void executeUpdate(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();

        }
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users" +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                " name TEXT NOT NULL," +
                " lastName TEXT NOT NULL," +
                " age TINYINT NOT NULL)";
        executeUpdate(sql);
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        executeUpdate(sql);
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format(
                "INSERT INTO `MySQL_dbTest`.`Users` (`name`, `lastName`, `age`) VALUES ('%s', '%s', '%d');",
                name,
                lastName,
                age
        );
        executeUpdate(sql);
    }

    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM Users WHERE id ='%d'", id);
        executeUpdate(sql);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        List<User> result = new ArrayList<>();
        int count = 0;
        try {
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new User(
                                resultSet.getString("name"),
                                resultSet.getString("lastName"),
                                resultSet.getByte("age")
                        )
                );
                result.get(count).setId(resultSet.getLong("id"));
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE Users";
        executeUpdate(sql);
    }
}
