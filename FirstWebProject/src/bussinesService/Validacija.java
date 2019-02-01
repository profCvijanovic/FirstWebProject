package bussinesService;

public class Validacija {
	
	public boolean daLiJePrazno(String polje) {
		
		if(polje !="" && polje != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean daLiJePassIsti(String password, String repeatedPassword) {
		
		if(password.equals(repeatedPassword)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	

}
