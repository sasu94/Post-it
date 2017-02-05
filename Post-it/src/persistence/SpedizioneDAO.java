package persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Spedizione;

public class SpedizioneDAO {
	private SessionFactory factory;

	public SpedizioneDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public void nuovaListaSpedizioni(List<Spedizione> l) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Spedizione s : l)
				session.save(s);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public boolean checkCodice(String codice) {
		Session session = factory.openSession();
		try {
			session.createQuery("FROM Spedizione where codice=:codice").setParameter("codice", codice)
					.getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public void saveSpedizione(Spedizione s) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void ritarda(Date date) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createQuery(
					"update Spedizione set dataPrevistaConsegna=:newDate where dataPrevistaConsegna=:data and stato='da Consegnare'")
					.setParameter("data", new Date()).setParameter("newDate", date).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Spedizione> getSpedizioniDaConsegnare() {
		Session session = factory.openSession();
		List<Spedizione> s = null;
		try {
			s = session.createQuery("FROM Spedizione where stato='da Consegnare'").getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return s;
	}

	public void assegnaCorriereASpedizione(String corriere, String spedizione) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createQuery("update Spedizione set corriere=:corriere where codice=:codice")
					.setParameter("corriere", corriere).setParameter("codice", spedizione).executeUpdate();
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