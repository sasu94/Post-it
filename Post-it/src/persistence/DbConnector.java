package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

	public UtenteDAO getUtenteDAO() {
		return new UtenteDAO(factory);
	}

	public FeedbackDAO getFeedbackDAO() {
		return new FeedbackDAO(factory);
	}

	public ResoDAO getResoDAO() {
		return new ResoDAO(factory);
	}

	public CorriereDAO getCorriereDAO() {
		return new CorriereDAO(factory);
	}

	public SpedizioneDAO getSpedizioneDAO() {
		return new SpedizioneDAO(factory);
	}

	public InformazioniConsegnaDAO getInformazioniConsegnaDAO() {
		return new InformazioniConsegnaDAO(factory);
	}

	public static DbConnector getInstance() {
		if (instance == null)
			instance = new DbConnector();
		return instance;
	}
}
