package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.InformazioniConsegna;
import persistence.CorriereDAO;
import persistence.DbConnector;
import persistence.FeedbackDAO;
import persistence.InformazioniConsegnaDAO;
import persistence.SpedizioneDAO;

@WebServlet("/Dirigente")
public class Dirigente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FeedbackDAO f;
	CorriereDAO c;
	InformazioniConsegnaDAO i;
	SpedizioneDAO s;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init() {
		DbConnector instance = DbConnector.getInstance();
		f = instance.getFeedbackDAO();
		c = instance.getCorriereDAO();
		i = instance.getInformazioniConsegnaDAO();
		s = instance.getSpedizioneDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (request.getParameter("action")) {
		case "report":
			request.setAttribute("corrieri", c.getCorrieri());
			forwardOnJsp(request, response, "/jsp/Report.jsp");
			break;
		case "feedback":
			request.setAttribute("feedback", f.getFeedback());
			forwardOnJsp(request, response, "/jsp/LeggiFeedback.jsp");
			break;
		case "manage":
			request.setAttribute("spedizioni", s.getSpedizioniDaConsegnare());
			request.setAttribute("corrieri", c.getCorrieriNonAssegnati());
			forwardOnJsp(request, response, "/jsp/ShippingManager.jsp");
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
		if (request.getParameter("corriere") != null) {
			DateTime inizio = new DateTime(request.getParameter("dataInizio"));
			DateTime fine = new DateTime(request.getParameter("dataFine"));

			List<InformazioniConsegna> l = i.getReportbyCorriere(request.getParameter("corriere"), inizio.toDate(),
					fine.toDate());
			JSONArray a = new JSONArray();
			for (InformazioniConsegna i : l) {
				JSONObject o = new JSONObject(i);
				a.put(o);
			}
			response.getWriter().print(a);
		} else if (request.getParameter("tutti") != null) {
			DateTime inizio = new DateTime(request.getParameter("dataInizio"));
			DateTime fine = new DateTime(request.getParameter("dataFine"));
			List<InformazioniConsegna> l = i.getTuttiReport();
			JSONArray a = new JSONArray();
			for (InformazioniConsegna i : l) {
				JSONObject o = new JSONObject(i);
				a.put(o);
			}
			response.getWriter().print(a);
		} else if (request.getParameter("dataInizio") != null) {
			DateTime inizio = new DateTime(request.getParameter("dataInizio"));
			DateTime fine = new DateTime(request.getParameter("dataFine"));
			JSONObject o = new JSONObject();
			try {
				o.put("res", fine.isAfter(inizio));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().print(o);

		} else if (request.getParameter("delay") != null) {
			DateTime newDate = new DateTime(request.getParameter("delay"));
			JSONObject o = new JSONObject();
			try {
				boolean b = newDate.isAfterNow();
				o.put("bool", b);

				if (b) {
					boolean giorno = newDate.getDayOfWeek() == 6 || newDate.getDayOfWeek() == 7;
					o.put("giorno", giorno);

					if (!giorno)
						s.ritarda(newDate.toDate());
				}

				response.getWriter().print(o.toString());

			} catch (Exception e) {

			}

		} else if (request.getParameter("par") != null) {
			String corriere = request.getParameter("cor");
			for (String string : request.getParameterValues("spedizioni")) {
				s.assegnaCorriereASpedizione(corriere, string);
			}
			response.sendRedirect("Dirigente?action=manage");
		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
