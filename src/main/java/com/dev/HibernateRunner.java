package com.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        /*BlockingDeque<Connection> pool = null;
        var connection1 = pool.take();
        var connection = DriverManager
                .getConnection("db.url", "db.username", "db.password");*/

        var configuration = new Configuration();
        configuration.configure();

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            System.out.println("OK");
        }
    }
}
