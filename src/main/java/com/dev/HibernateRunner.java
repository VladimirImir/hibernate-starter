package com.dev;

import com.dev.converter.BirthdateConverter;
import com.dev.entity.Birthdate;
import com.dev.entity.Role;
import com.dev.entity.User;
import com.dev.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {

        var user = User.builder()
                .username("ivan1@gmail.com")
                .lastname("Ivanov")
                .firstname("Ivan")
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (var session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }
            try (var session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                user.setFirstname("Petr");
                //session2.delete(user);

                Object mergedUser = session2.merge(user);
                //session2.refresh(user);

                session2.getTransaction().commit();
            }
        }

        /*try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            *//*User user = User.builder()
                    .username("ivan1@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .info("""
                            {
                                "name": "Ivan",
                                "id": 25
                            }
                            """)
                    .birthDate(new Birthdate(LocalDate.of(2000, 1, 19)))
                    .role(Role.ADMIN)
                    .build();
            session.save(user);*//*
            var user = session.get(User.class, "ivan1@gmail.com");
            user.setLastname("Petrov2");
            session.flush();

            System.out.println(session.isDirty());

            *//*session.evict(user1);
            session.clear();
            session.close();*//*

            session.getTransaction().commit();
        }*/
    }


}