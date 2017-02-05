package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.InformazioniConsegna;
import persistence.DbConnector;
import persistence.FeedbackDAO;
import persistence.InformazioniConsegnaDAO;
import persistence.SpedizioneDAO;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FeedbackDAO f;
	InformazioniConsegnaDAO i;
	SpedizioneDAO s;

	@Override
	public void init() throws ServletException {
		DbConnector instance = DbConnector.getInstance();
		f = instance.getFeedbackDAO();
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
		// TODO Auto-generated method stub
		switch (request.getParameter("action")) {
		case "feedback":
			forwardOnJsp(request, response, "/jsp/InserisciFeedback.jsp");
			break;
		case "track":
			forwardOnJsp(request, response, "/jsp/TracciaSpedizione.jsp");
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
		if (request.getParameter("codiceTracking") != null) {
			try {
				String codice = request.getParameter("codiceTracking");
				boolean checkCodice = s.checkCodice(codice);
				JSONObject o = new JSONObject();
				o.put("exist", checkCodice);
				if (checkCodice) {
					List<InformazioniConsegna> tracking = i.getTracking(codice);
					JSONArray a = new JSONArray();
					for (InformazioniConsegna i : tracking) {
						JSONObject ob = new JSONObject(i);
						a.put(ob);
					}

					String lastPosition = i.getLastPosition(codice);
					o.put("last", lastPosition);
					o.put("list", a);

				}
				response.getWriter().print(o.toString());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getParameter("par") != null) {
			if (request.getParameter("feedback") != null) {
				f.nuovoFeedback(request.getParameter("codice"), request.getParameter("argomento"),
						request.getParameter("commento"));
				response.sendRedirect("/Post-it");
			}
		}

	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
