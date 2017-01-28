package util;

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
import org.hibernate.cfg.Configuration;

import model.Feedback;
import model.Giacenza;
import model.InformazioniConsegna;
import model.Reso;
import model.Spedizione;
import model.Utente;

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
}
