
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Giacenza;
import model.Reso;
import model.Spedizione;
import util.DbConnector;

/**
 * Servlet implementation class forwardServlet
 */
@WebServlet("/Segretaria")
public class Segretaria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Segretaria() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		switch (request.getParameter("action")) {
		case "inserimento":
			request.setAttribute("corrieri", DbConnector.getInstance().getCorrieri());
			forwardOnJsp(request, response, "/jsp/InserisciSpedizione.jsp");
			break;
		case "reso":
			HashMap<String, Reso> r = DbConnector.getInstance().getResi();
			request.getSession().setAttribute("resi", r);
			HashMap<String, Reso> g = DbConnector.getInstance().getGiacenze();
			request.getSession().setAttribute("giacenze", g);
			forwardOnJsp(request, response, "/jsp/GestioneResi.jsp");
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("spedizioni") != null) {
			ObjectMapper o = new ObjectMapper();
			DbConnector.getInstance().nuovaListaSpedizioni(
					o.readValue(request.getParameter("spedizioni"), new TypeReference<List<Spedizione>>() {
					}));

		} else if (request.getParameter("date") != null) {
			String s = request.getParameter("date");
			s = s.substring(1, s.length() - 1);
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTime d = new DateTime(formatter.parseDateTime(s));
			String errore = "0";
			if (!d.isAfterNow())
				errore = "1";
			else if (d.getDayOfWeek() == 6 || d.getDayOfWeek() == 7)
				errore = "2";
			response.getWriter().write(errore);
		} else if (request.getParameter("reso") != null) {
			String s = request.getParameter("reso");
			s = s.substring(1, s.length() - 1);
			HashMap<String, Reso> r = (HashMap<String, Reso>) request.getSession().getAttribute("resi");
			DbConnector.getInstance().risolviReso(r.get(s));
			r.remove(s);
		} else if (request.getParameter("giacenza") != null) {
			ObjectMapper m = new ObjectMapper();
			Giacenza g = m.readValue(request.getParameter("giacenza"), Giacenza.class);
			HashMap<String, Reso> giacenze = (HashMap<String, Reso>) request.getSession().getAttribute("giacenze");
			DbConnector.getInstance().risolviReso(giacenze.get(g.getCodice()));
			DbConnector.getInstance().saveGiacenza(g);
			giacenze.remove(g.getCodice());
			response.getWriter().print("bra");
		} else if (request.getParameter("allGiacenze") != null) {
			String s = request.getParameter("allGiacenze");
			s = s.substring(1, s.length() - 1);
			HashMap<String, Reso> giacenze = (HashMap<String, Reso>) request.getSession().getAttribute("giacenze");
			DbConnector.getInstance().saveAlleGiacenze(giacenze.values(), Integer.parseInt(s));
			giacenze.clear();
		} else if (request.getParameter("allResi") != null) {
			HashMap<String, Reso> r = (HashMap<String, Reso>) request.getSession().getAttribute("resi");
			DbConnector.getInstance().RisolviTuttiResi(r.values());
			r.clear();
		} else if (request.getParameter("newReso") != null) {
			ObjectMapper o = new ObjectMapper();
			Reso r = o.readValue(request.getParameter("newReso"), Reso.class);
			Spedizione s = o.readValue(request.getParameter("spedizione"), Spedizione.class);
			r.setData(new Date());
			DbConnector.getInstance().saveSpedizione(s);
			DbConnector.getInstance().risolviReso(r);
		} else if (request.getParameter("codice") != null) {
			if (DbConnector.getInstance().checkCodice(request.getParameter("codice")))
				response.getWriter().print("esistente");
			else
				response.getWriter().print("non esistente");
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
