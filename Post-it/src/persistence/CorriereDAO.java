package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Utente;

public class CorriereDAO {
	private SessionFactory factory;

	public CorriereDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public List<Utente> getCorrieri() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Utente> u = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			u = session.createQuery("FROM Utente where Tipologia='c'").getResultList();
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

	public List<Utente> getCorrieriNonAssegnati() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Utente> u = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			u = session
					.createQuery(
							"From Utente where tipologia='c' and Email not in (select corriere from Spedizione where stato='da Consegnare')")
					.getResultList();
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
}
