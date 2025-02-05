package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS human_2 (id int NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(50), " +
                "lastName varchar(50), " +
                "age int)";
        process(sql);

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS human_2";
        process(sql);
    }

    @Override
    public void saveUser(String name, String lastName, Integer age) {
        try (Session session = Util.connectionHibernate()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(Integer id) {
        try (Session session = Util.connectionHibernate()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser;
        try (Session session = Util.connectionHibernate()) {
            session.beginTransaction();
            listUser = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
        }
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM human_2";
        process(sql);
    }

    private void process(String sql) {
        try (Session session = Util.connectionHibernate()) {
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
