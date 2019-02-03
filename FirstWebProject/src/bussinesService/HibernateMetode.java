package bussinesService;

import java.util.List;

import javax.persistence.Query;

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
	
	public boolean daLiPostojiUser(String userName) {
		
		Session session = getSf().openSession();
			session.beginTransaction();
			
			try {
				Query query = session.createQuery("SELECT idUser FROM User WHERE userName= :userNameSaUlaza");
				query.setParameter("userNameSaUlaza", userName);
				List<Integer>listaUserNameova  = (List<Integer>)query.getResultList();
				if((int)listaUserNameova.get(0) != 0) {
					session.getTransaction().commit();
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}finally {
				session.close();
			}
	}
	

	public boolean daLiJeDobarPassword(String userName, String password) {
		
		Session session = getSf().openSession();
			session.beginTransaction();
		Validacija v = new Validacija();
			try {
				Query query = session.createQuery("SELECT password FROM User WHERE userName= :userNameSaUlaza");
				query.setParameter("userNameSaUlaza", userName);
				List<String>listaPassworda  = (List<String>)query.getResultList();
				if(listaPassworda != null) {
					String sifrovaniPassword = v.konvertujPasswordUSifru(password);
					if(sifrovaniPassword.equals((String)listaPassworda.get(0))) {
						session.getTransaction().commit();
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
				
			} catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}finally {
				session.close();
			}
	}
	
	public User dajUsera(String userName) {
		
		Session session = getSf().openSession();
			session.beginTransaction();
		Validacija v = new Validacija();
			try {
				Query query = session.createQuery("FROM User WHERE userName= :userNameSaUlaza");
				query.setParameter("userNameSaUlaza", userName);
				List<User>listaUsera  = (List<User>)query.getResultList();
				User user = (User)listaUsera.get(0);
				session.getTransaction().commit();
				return user;
			} catch (Exception e) {
				session.getTransaction().rollback();
				return null;
			}finally {
				session.close();
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
