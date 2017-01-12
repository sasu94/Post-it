package util;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DAO.Utente;

public class DbConnector {
	private static SessionFactory factory;
	static DbConnector instance;

	public DbConnector() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	public static DbConnector getInstance() {
		if (instance == null)
			instance = new DbConnector();
		return instance;
	}

	public Utente getUtente(String mail, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		Utente u = null;
		try {
			tx = session.beginTransaction();
			u = (Utente) session.createQuery("FROM Utente where Email=:email and Password=:passw")
					.setParameter("email", mail).setParameter("passw", password).getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
		return u;

	}

	public void registraNuovoAccount(String email, String password, String nome, String cognome) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Utente utente = new Utente(email, password, nome, cognome, "N");
			session.save(utente);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
