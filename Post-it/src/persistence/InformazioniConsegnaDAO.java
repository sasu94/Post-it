package persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.InformazioniConsegna;

public class InformazioniConsegnaDAO {
	private SessionFactory factory;

	public InformazioniConsegnaDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public List<InformazioniConsegna> getReportbyCorriere(String corriere, Date inizio, Date fine) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<InformazioniConsegna> l = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			l = session
					.createQuery(
							"FROM InformazioniConsegna where data>:inizio and data<:fine and idSpedizione in(select codice from Spedizione where corriere=:corriere)")
					.setParameter("corriere", corriere).setParameter("inizio", inizio).setParameter("fine", fine)
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
		return l;
	}

	public List<InformazioniConsegna> getTuttiReport() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<InformazioniConsegna> l = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			l = session
					.createQuery(
							"FROM InformazioniConsegna where idSpedizione in(select codice from Spedizione where corriere is not null)")
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
		return l;
	}

	public List<InformazioniConsegna> getTracking(String codice) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<InformazioniConsegna> l = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			l = session.createQuery("FROM InformazioniConsegna where idSpedizione=:codice")
					.setParameter("codice", codice).getResultList();
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

	public String getLastPosition(String codice) {
		Session session = factory.openSession();
		Transaction tx = null;
		String s = null;
		try {
			tx = session.beginTransaction();
			s = (String) session
					.createQuery(
							"select posizione FROM InformazioniConsegna where Data=(select Max(data) from InformazioniConsegna where idSpedizione=:codice)")
					.setParameter("codice", codice).getSingleResult();

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
		return s;

	}

}