package bg.jwd.ejb;

import javax.ejb.Local;

@Local
public interface WebBank {
	Double deposit(String client,Double amount, String currency);
	
	Double withdraw(String client,Double amount, String currency);
}
