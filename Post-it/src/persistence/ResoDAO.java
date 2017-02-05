package persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.DateTime;

import model.Giacenza;
import model.InformazioniConsegna;
import model.Reso;

public class ResoDAO {
	private SessionFactory factory;

	public ResoDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public HashMap<String, Reso> getResi() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<InformazioniConsegna> i = new ArrayList<>();
		HashMap<String, Reso> r = new HashMap<>();
		try {
			tx = session.beginTransaction();
			i = session
					.createQuery(
							"FROM InformazioniConsegna where Informazioni not like '%Giacenza%' and Informazioni not like '%Consegna Completata%' and idSpedizione not in(select codice FROM Reso)")
					.getResultList();
			for (InformazioniConsegna inf : i) {
				r.put(inf.getIdSpedizione(), new Reso(inf.getIdSpedizione(), inf.getInformazioni(), inf.getData()));
			}
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
		return r;
	}

	public HashMap<String, Reso> getGiacenze() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<InformazioniConsegna> i = new ArrayList<>();
		HashMap<String, Reso> r = new HashMap<>();
		try {
			tx = session.beginTransaction();
			i = session.createQuery("FROM InformazioniConsegna where Informazioni like '%Giacenza%'").getResultList();
			for (InformazioniConsegna inf : i) {
				r.put(inf.getIdSpedizione(), new Reso(inf.getIdSpedizione(), inf.getInformazioni(), inf.getData()));
			}
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
		return r;
	}

	public Reso getGiacenzabyCod(String codice) {
		Session session = factory.openSession();
		Transaction tx = null;
		Reso r = new Reso();
		try {
			tx = session.beginTransaction();
			r = (Reso) session.createQuery("FROM Reso where codice=:codice").setParameter("codice", codice)
					.getSingleResult();
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
		return r;
	}

	public void risolviReso(Reso r) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(r);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void saveAlleGiacenze(Collection<Reso> l, int giorni) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Reso r : l) {
				session.save(new Giacenza(r.getCodice(), giorni));
			}
			RisolviTuttiResi(l);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void saveGiacenza(Giacenza g) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(g);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void RisolviTuttiResi(Collection<Reso> resi) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Reso r : resi)
				session.save(r);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void addReso(InformazioniConsegna i) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(i);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void ritiroGiacenza(String codice) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Giacenza g = new Giacenza();
			g.setCodice(codice);
			session.delete(g);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public boolean giacenzaScaduta(String codice) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			int g = (int) session.createQuery("select giorni from Giacenza where codice=:codice")
					.setParameter("codice", codice).getSingleResult();
			Date d = (Date) session.createQuery("select data FROM Reso where codiceSpedizione=:codice")
					.setParameter("codice", codice).getSingleResult();

			DateTime date = new DateTime(d);
			date = date.plusDays(g);
			tx.commit();
			return (!date.isAfterNow());
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (NoResultException e) {
			return true;
		} finally {
			session.close();
		}
		return false;
	}

	public boolean checkCodiceGiacenza(String codice) {
		Session session = factory.openSession();
		try {
			session.createQuery("FROM Giacenza where codice=:codice").setParameter("codice", codice).getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			return false;
		} finally {
			session.close();
		}
		return true;
	}

}
