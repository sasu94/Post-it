package servlet;

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
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Giacenza;
import model.Reso;
import model.Spedizione;
import persistence.CorriereDAO;
import persistence.DbConnector;
import persistence.ResoDAO;
import persistence.SpedizioneDAO;

/**
 * Servlet implementation class forwardServlet
 */
@WebServlet("/Segretaria")
public class Segretaria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CorriereDAO c;
	SpedizioneDAO s;
	ResoDAO r;

	@Override
	public void init() throws ServletException {
		c = DbConnector.getInstance().getCorriereDAO();
		s = DbConnector.getInstance().getSpedizioneDAO();
		r = DbConnector.getInstance().getResoDAO();
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
			request.setAttribute("corrieri", c.getCorrieri());
			forwardOnJsp(request, response, "/jsp/InserisciSpedizione.jsp");
			break;
		case "reso":
			HashMap<String, Reso> reso = r.getResi();
			request.getSession().setAttribute("resi", reso);
			HashMap<String, Reso> g = r.getGiacenze();
			request.getSession().setAttribute("giacenze", g);
			forwardOnJsp(request, response, "/jsp/GestioneResi.jsp");
			break;
		case "ritiro":
			forwardOnJsp(request, response, "/jsp/RitiroReso.jsp");
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
			s.nuovaListaSpedizioni(
					o.readValue(request.getParameter("spedizioni"), new TypeReference<List<Spedizione>>() {
					}));

		} else if (request.getParameter("date") != null) {
			String s = request.getParameter("date");
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
			HashMap<String, Reso> reso = (HashMap<String, Reso>) request.getSession().getAttribute("resi");
			r.risolviReso(reso.get(s));
			reso.remove(s);
		} else if (request.getParameter("giacenza") != null) {
			ObjectMapper m = new ObjectMapper();
			Giacenza g = m.readValue(request.getParameter("giacenza"), Giacenza.class);
			HashMap<String, Reso> giacenze = (HashMap<String, Reso>) request.getSession().getAttribute("giacenze");
			r.risolviReso(giacenze.get(g.getCodice()));
			r.saveGiacenza(g);
			giacenze.remove(g.getCodice());
			response.getWriter().print("bra");
		} else if (request.getParameter("allGiacenze") != null) {
			String s = request.getParameter("allGiacenze");
			HashMap<String, Reso> giacenze = (HashMap<String, Reso>) request.getSession().getAttribute("giacenze");
			r.saveAlleGiacenze(giacenze.values(), Integer.parseInt(s));
			giacenze.clear();
		} else if (request.getParameter("allResi") != null) {
			HashMap<String, Reso> resi = (HashMap<String, Reso>) request.getSession().getAttribute("resi");
			r.RisolviTuttiResi(resi.values());
			resi.clear();
		} else if (request.getParameter("newReso") != null) {
			ObjectMapper o = new ObjectMapper();
			Reso reso = o.readValue(request.getParameter("newReso"), Reso.class);
			Spedizione spedizione = o.readValue(request.getParameter("spedizione"), Spedizione.class);
			reso.setData(new Date());
			s.saveSpedizione(spedizione);
			r.risolviReso(reso);
		} else if (request.getParameter("codice") != null) {
			if (s.checkCodice(request.getParameter("codice")))
				response.getWriter().print("esistente");
			else
				response.getWriter().print("non esistente");
		} else {
			if (request.getParameter("ritiro") != null) {
				String codice = request.getParameter("ritiro");
				JSONObject o = new JSONObject();
				try {
					boolean checkCodice = r.checkCodiceGiacenza(codice);
					o.put("exist", checkCodice);

					if (checkCodice) {
						if (!r.giacenzaScaduta(codice)) {
							r.ritiroGiacenza(codice);
						} else
							o.put("scaduta", true);
					}
					response.getWriter().print(o.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
