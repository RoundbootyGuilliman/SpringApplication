package app.dao.impl;

import app.dao.UserDAO;
import app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@PreDestroy
	private void destroy() {
		if (sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.saveOrUpdate(user);

		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<User> newsList = session.createQuery("from User").list();

		session.getTransaction().commit();
		session.close();
		return newsList;
	}

	@Override
	public User getByEmail(String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		User user = (User) session.createQuery("from User where email=:email").setParameter("email", email).uniqueResult();

		session.getTransaction().commit();
		session.close();
		return user;
	}


	@Override
	public void deleteUser(String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(getByEmail(email));

		session.getTransaction().commit();
		session.close();
	}
}