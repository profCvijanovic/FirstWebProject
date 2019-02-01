package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussinesService.HibernateMetode;
import bussinesService.Validacija;

/**
 * Servlet implementation class RegistracioniServlet
 */
@WebServlet(description = "servlet za registraciju", urlPatterns = { "/RegistracioniServlet" })
public class RegistracioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistracioniServlet() {
        // konstruktor, moze a ne mora!
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String ime = request.getParameter("ime");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String repeatedPassword = request.getParameter("repeatedPassword");
		
		Validacija validacija = new Validacija();
		HibernateMetode metode = new HibernateMetode();
		if(validacija.daLiJePrazno(password)&& validacija.daLiJePrazno(ime)&& validacija.daLiJePrazno(repeatedPassword)&& validacija.daLiJePrazno(userName)) {
			if(validacija.daLiJePassIsti(password, repeatedPassword)) {
				metode.ubaciUsera(ime, userName, repeatedPassword);
				response.sendRedirect("index.html");
			}else {
				response.sendRedirect("htmlovi/registracija.html");
			}
		
		}else {
			response.sendRedirect("htmlovi/registracija.html");
		}
		
	}

}
