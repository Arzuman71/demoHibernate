

import com.examplehibernate.demohibernate.model.User;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
      //  saveUserAngGet();
      //  getAllUser();
      //  getUsersByName();
    }

    private static void saveUserAngGet() {
        final Session session = getSession();
        try {
            User user = User.builder()
                    .name("petros")
                    .surname("petrosyan")
                    .build();
            Transaction t = session.beginTransaction();
            session.save(user);
            int id = user.getId();
            User userGet = session.get(User.class, id);
            t.commit();
            System.out.println(userGet);

        } finally {
            session.close();
        }
    }

    private static void getAllUser() {
        final Session session = getSession();
        try {
            Transaction t = session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            t.commit();
            System.out.println(users);
        } finally {
            session.close();
        }
    }

    private static void getUsersByName() {
        final Session session = getSession();

        try {
            Transaction t = session.beginTransaction();
            List<User> usersPetros = session.createQuery("from User where name = 'petros'").getResultList();
            t.commit();
            System.out.println("usersPetros-------" + usersPetros);
        } finally {
            session.close();
        }
    }
}