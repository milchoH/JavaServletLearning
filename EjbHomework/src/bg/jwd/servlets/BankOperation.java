package bg.jwd.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.jwd.ejb.WebBank;

@WebServlet("/BankOperation")
public class BankOperation extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private WebBank webBank;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String client = req.getParameter("client");
		String operation = req.getParameter("operation");
		Double amount = Double.parseDouble(req.getParameter("amount"));
		String currency = req.getParameter("currency");
		Double currentAmount;
		
		if("D".equals(operation)){
			currentAmount = webBank.deposit(client, amount, currency);
		}else{
			currentAmount = webBank.withdraw(client, amount, currency);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/BankOperation.jsp");
		req.setAttribute("client", client);
		req.setAttribute("currentAmount", currentAmount);

		dispatcher.forward(req, resp);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}