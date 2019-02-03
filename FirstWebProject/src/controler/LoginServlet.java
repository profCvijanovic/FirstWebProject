package controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussinesService.HibernateMetode;
import bussinesService.Validacija;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "ovo je servlet za login", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		Validacija v = new Validacija();
		HibernateMetode hm = new HibernateMetode();
		
		if(v.daLiNijePrazno(userName) && v.daLiNijePrazno(password)) {
			if(hm.daLiPostojiUser(userName)) {
				if(hm.daLiJeDobarPassword(userName, password)) {
					User user = hm.dajUsera(userName);
					request.setAttribute("user", user);
					RequestDispatcher dispatcher;
					if(user.getIdUser()==1) {
						dispatcher = request.getRequestDispatcher("jsp/adminStrana.jsp");
						dispatcher.forward(request, response);
					}else {
						dispatcher = request.getRequestDispatcher("jsp/userStrana.jsp");
						dispatcher.forward(request, response);
					}
				}else {
					response.sendRedirect("htmlovi/loginPasswordLos.html");
				}
			}else {
				response.sendRedirect("htmlovi/loginUserNameLos.html");
			}
		}else {
			response.sendRedirect("htmlovi/loginPrazno.html");
		}
	}

}
