package bussinesService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.User;

public class HibernateMetode {
	
	private SessionFactory sf = new Configuration().configure().buildSessionFactory();

	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	public void ubaciUsera(String ime, String userName, String password) {
		User user = new User();
			user.setName(ime);
			user.setUserName(userName);
			user.setPassword(password);
			user.setBalance(1000);//svaki user prilikom registracije dobija 1000 balasa na poklon 
		Session session = getSf().openSession();
		session.beginTransaction();
		try {
			session.save(user);
			System.out.println("Uspesno ste uneli usera");
			session.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Neuspesan unos!");
			session.getTransaction().rollback();
		}
		session.close();
	}
	
	
	
	

}
