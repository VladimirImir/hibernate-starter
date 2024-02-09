package com.dev;

import com.dev.entity.Role;
import com.dev.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.BlockingDeque;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        /*BlockingDeque<Connection> pool = null;
        var connection1 = pool.take();
        var connection = DriverManager
                .getConnection("db.url", "db.username", "db.password");*/

        var configuration = new Configuration();
        //configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        //configuration.addAnnotatedClass(User.class);
        configuration.configure();

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user = User.builder()
                    .username("ivan@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(LocalDate.of(2000, 1, 11))
                    .age(20)
                    .role(Role.ADMIN)
                    .build();
            session.save(user);

            session.getTransaction().commit();
        }
    }
}
