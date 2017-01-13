
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Utente;
import util.DbConnector;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbConnector d;

	public Login() {
		d = DbConnector.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("user") != null) {
			String s = request.getParameter("user");
			s = s.substring(1, s.length() - 1);
			String[] par = s.split(";");
			Utente a = d.getUtente(par[0], par[1]);
			if (a != null) {
				response.getWriter().print("true;" + a.getNome() + ";" + a.getTipologia());
				request.getSession().setAttribute("account", a);
			} else
				response.getWriter().print("false");
		} else if (request.getParameter("session") != null) {
			Utente a = (Utente) request.getSession().getAttribute("account");
			if (a != null) {
				response.getWriter().print("true;" + a.getNome() + ";" + a.getTipologia());
				request.getSession().setAttribute("account", a);
			} else
				response.getWriter().print("false");
		} else if (request.getParameter("logout") != null) {
			request.getSession().removeAttribute("account");
		} else if (request.getParameter("register") != null) {
			String s = request.getParameter("register");
			s = s.substring(1, s.length() - 1);
			String[] par = s.split(";");
			d.registraNuovoAccount(par[0], par[1], par[2], par[3]);
		}
	}

}
