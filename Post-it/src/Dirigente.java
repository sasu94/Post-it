
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
import util.DbConnector;

/**
 * Servlet implementation class Dirigente
 */
@WebServlet("/Dirigente")
public class Dirigente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dirigente() {
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
		case "report":
			request.setAttribute("corrieri", DbConnector.getInstance().getCorrieri());
			forwardOnJsp(request, response, "/jsp/Report.jsp");
			break;
		case "feedback":
			request.setAttribute("feedback", DbConnector.getInstance().getFeedback());
			forwardOnJsp(request, response, "/jsp/LeggiFeedback.jsp");
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

			List<InformazioniConsegna> l = DbConnector.getInstance()
					.getReportbyCorriere(request.getParameter("corriere"), inizio.toDate(), fine.toDate());
			JSONArray a = new JSONArray();
			for (InformazioniConsegna i : l) {
				JSONObject o = new JSONObject(i);
				a.put(o);
			}
			response.getWriter().print(a);
		} else if (request.getParameter("tutti") != null) {
			DateTime inizio = new DateTime(request.getParameter("dataInizio"));
			DateTime fine = new DateTime(request.getParameter("dataFine"));
			List<InformazioniConsegna> l = DbConnector.getInstance().getTuttiReport();
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

		}
	}

	private void forwardOnJsp(HttpServletRequest req, HttpServletResponse resp, String nextJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
		dispatcher.forward(req, resp);
	}

}
