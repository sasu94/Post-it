package persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Feedback;

public class FeedbackDAO {
	private SessionFactory factory;

	public FeedbackDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public void nuovoFeedback(String codice, String argomento, String commento) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Feedback f = new Feedback(codice, argomento, commento);
			f.setData(new Date());
			session.save(f);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<Feedback> getFeedback() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Feedback> l = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			l = session.createQuery("FROM Feedback order by Data desc").getResultList();
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
		return l;
	}
}
