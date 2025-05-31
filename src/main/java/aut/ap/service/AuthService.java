package aut.ap.service;

import aut.ap.model.User;
import aut.ap.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AuthService {

    public boolean register(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (user.getPassword().length() < 8) {
                System.out.println("Weak password");
                return false;
            }

            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", user.getEmail());
            if (!query.list().isEmpty()) {
                System.out.println("An account with this email already exists");
                return false;
            }

            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            System.out.println("User registered successfully.");
            return true;
        }
    }

    public User login(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.uniqueResult();
        }
    }
}
