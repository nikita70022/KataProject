package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userDao = new UserServiceImpl();

        userDao.createUsersTable();

        userDao.saveUser("name1", "lastName1", (byte) 21);
        System.out.println(String.format("User с именем - %s добавлен в базу данных", "name1"));

        userDao.saveUser("name2", "lastName2", (byte) 22);
        System.out.println(String.format("User с именем - %s добавлен в базу данных", "name2"));

        userDao.saveUser("name3", "lastName3", (byte) 23);
        System.out.println(String.format("User с именем - %s добавлен в базу данных", "name3"));

        userDao.saveUser("name4", "lastName4", (byte) 24);
        System.out.println(String.format("User с именем - %s добавлен в базу данных", "name4"));

        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
