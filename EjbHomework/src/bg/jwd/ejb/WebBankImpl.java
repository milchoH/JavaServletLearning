package bg.jwd.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

import bg.jwd.entity.Account;
import bg.jwd.entity.ExchangeRate;

@Stateful
public class WebBankImpl implements WebBank {
	
	private static final Map<String, Account> bankAccounts = new HashMap<>();
	private static final Map<String, ExchangeRate> exchangeRates = new HashMap<>();
	
	private Map<String, Double> initialAmounts = new HashMap<>();
	private Map<String, Double> totalWithdraws = new HashMap<>();
	
	static {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setCurrency("BGN");
		exchangeRate.setRate(1.95);
		exchangeRate.setDate(new Date());
		exchangeRates.put("EUR", exchangeRate);
		
	}

	@Override
	public Double deposit(String client, Double amount, String currency) {
		Account userAcc = bankAccounts.get(client);
		
		if(userAcc==null){
			userAcc=new Account();
			userAcc.setCurrency("BGN");
		}
		
		if(!userAcc.getCurrency().equals(currency)){
			ExchangeRate exchangeRate = exchangeRates.get(currency);
			amount *= exchangeRate.getRate();
		}
		
		Double currentAmount = userAcc.getAmount(); 
		
		if(currentAmount == null){
			currentAmount = 0.0;
		}
		
		currentAmount += amount;
		userAcc.setAmount(currentAmount);
		
		bankAccounts.put(client, userAcc);
		initialAmounts.put(client,currentAmount);
		
		return currentAmount;
	}

	@Override
	public Double withdraw(String client, Double amount, String currency) {
		Account userAcc = bankAccounts.get(client);
		Double currentAmount = userAcc.getAmount();
		Double totalWithdraw = totalWithdraws.get(client);
		Double initialAmount = initialAmounts.get(client);
		
		if(totalWithdraw ==null){
			totalWithdraw = 0.0;
		}
		
		totalWithdraw += amount;
		
		if(currentAmount == null){
			return 0.0;
		}else if(currentAmount >= amount && totalWithdraw < initialAmount *0.5){
			currentAmount -=amount;
			userAcc.setAmount(currentAmount);
			bankAccounts.put(client, userAcc);

			totalWithdraws.put(client, totalWithdraw);
		}
		
	//	currentAmount -= amount;
	//	bankAccounts.put(client, currentAmount);
		return currentAmount;
	}
}